//
// Created by QITMAC0000562 on 2021/1/1.
//

#include "Player.h"
#include "Log.h"
#include "AudioChannel.h"

//加上extern "C"后，会指示编译器这部分代码按C语言的进行编译，而不是C++的
//参考：https://blog.csdn.net/jiqiren007/article/details/5933599
extern "C" {
#include <libavformat/avformat.h>
}

//初始化列表helper(helper)，初始化成员变量
Player::Player(JavaCallHelper *helper) : helper(helper) {
    //avformat_network_init：全局地初始化网络组件，需要用到网络功能的时候需要调用。
    avformat_network_init();
}

//析构函数
Player::~Player() {
    avformat_network_deinit();
    delete helper;
    helper = 0;
    if (path) {
        delete[] path;
        path = 0;
    }
}

void Player::setDataSource(const char *path_) {
    path = static_cast< char *>(malloc(strlen(path_) + 1));
    memset((void *) path, 0, strlen(path) + 1);

    memcpy(path, path_, strlen(path_));
}

void Player::setWindow(ANativeWindow *window) {
    this->window = window;
    if (videoChannel) {
        videoChannel->setWindow(window);
    }
}

void *prepare_t(void *args) {
    Player *player = static_cast<Player *>(args);
    player->_prepare();
    return 0;
}

void Player::prepare() {
    //解析耗时，创建异步线程执行相关的准备工作
    //参考：https://www.runoob.com/cplusplus/cpp-multithreading.html
    pthread_create(&prepareTask, 0, prepare_t, this);
}

void Player::_prepare() {
    avFormatContext = avformat_alloc_context();

    //avformat_open_input：打开输出的流和读取头信息。需要使用avformat_close_input关闭打开的流。
    //参数3： 输入文件的封装格式 ，传null表示 自动检测格式。 avi / flv，传入指针的指针，通过其返回值
    //参数4： map集合，比如打开网络文件超时设置
    int ret = avformat_open_input(&avFormatContext, path, 0, 0);
    if (ret != 0) {
        LOGE("打开%s 失败，返回:%d 错误描述:%s", path, ret, av_err2str(ret));
        helper->onError(FFMPEG_CAN_NOT_OPEN_URL, THREAD_CHILD);
        goto ERROR;
    }

    //avformat_find_stream_info：读取视音频数据来获取一些相关的信息。
    ret = avformat_find_stream_info(avFormatContext, 0);
    if (ret < 0) {
        LOGE("查找媒体流 %s 失败，返回:%d 错误描述:%s", path, ret, av_err2str(ret));
        helper->onError(FFMPEG_CAN_NOT_FIND_STREAMS, THREAD_CHILD);
        goto ERROR;
    }

    // 得到视频时长，单位是秒
    duration = avFormatContext->duration / AV_TIME_BASE;
    // 这个媒体文件中有几个媒体流 (视频流、音频流)
    for (int i = 0; i < avFormatContext->nb_streams; ++i) {
        AVStream *avStream = avFormatContext->streams[i];
        // 解码信息
        AVCodecParameters *parameters = avStream->codecpar;
        //查找解码器
        AVCodec *dec = avcodec_find_decoder(parameters->codec_id);
        if (!dec) {
            helper->onError(FFMPEG_FIND_DECODER_FAIL, THREAD_CHILD);
            goto ERROR;
        }

        //avcodec_alloc_context3：创建AVCodecContext结构体。
        AVCodecContext *codecContext = avcodec_alloc_context3(dec);
        //avcodec_parameters_to_context：将音频流信息拷贝到新的AVCodecContext结构体中。
        if (avcodec_parameters_to_context(codecContext, parameters) < 0) {
            helper->onError(FFMPEG_CODEC_CONTEXT_PARAMETERS_FAIL, THREAD_CHILD);
            goto ERROR;
        }

        //多线程解码
        if (parameters->codec_type == AVMEDIA_TYPE_VIDEO) {
            codecContext->thread_count = 8;
        } else if (parameters->codec_type == AVMEDIA_TYPE_AUDIO) {
            codecContext->thread_count = 1;
        }

        //avcodec_open2：使用给定的AVCodec初始化AVCodecContext，打开解码器
        if (avcodec_open2(codecContext, dec, 0) != 0) {
            //打开失败
            helper->onError(FFMPEG_OPEN_DECODER_FAIL, THREAD_CHILD);
            goto ERROR;
        }

        if (parameters->codec_type == AVMEDIA_TYPE_AUDIO) {
            //音频
            audioChannel = new AudioChannel(i, helper, codecContext, avStream->time_base);
        } else if (parameters->codec_type == AVMEDIA_TYPE_VIDEO) {
            //视频
            //帧率
            double fps = av_q2d(avStream->avg_frame_rate);
            if (isnan(fps) || fps == 0) {
                fps = av_q2d(avStream->r_frame_rate);
            }
            if (isnan(fps) || fps == 0) {
                fps = av_q2d(av_guess_frame_rate(avFormatContext, avStream, 0));
            }

            videoChannel = new VideoChannel(i, helper, codecContext, avStream->time_base, fps);
            videoChannel->setWindow(window);
        }
    }
    // 如果媒体文件中没有音视频
    if (!videoChannel && !audioChannel) {
        //TODO audioChannel
        helper->onError(FFMPEG_NOMEDIA, THREAD_CHILD);
        goto ERROR;
    }

    ERROR:
    //回调Java中的onPrepare方法，告诉java准备好了，可以播放了，调用onStart()
    helper->onParpare(THREAD_CHILD);
    return;

    LOGE("失败释放");
    release();
}

void *start_t(void *args) {
    Player *player = static_cast<Player *>(args);
    player->_start();
    return 0;
}

void Player::start() {
    //1、读取媒体源的数据
    //2、根据数据类型放入Audio/VideoChannel的队列中
    isPlaying = 1;

    //分别启动视频和音频的播放
    if (videoChannel) {
        videoChannel->audioChannel = audioChannel;
        videoChannel->play();
    }

    if (audioChannel) {
        audioChannel->play();
    }

    //异步子线程读取视频和音频数据
    pthread_create(&startTask, 0, start_t, this);
}

void Player::_start() {
    int ret;
    while (isPlaying) {
        //av_packet_alloc：来创建一个AVPacket的实例，但该函数并不会为数据分配空间，其指向数据域的指针为NULL，通
        //常调用av_read_frame将流中的数据读取到AVPacket中。
        AVPacket *packet = av_packet_alloc();
        //av_read_frame：读取码流中的音频若干帧或者视频一帧。例如，解码视频的时候，每解码一个视频帧，需要先调用
        //av_read_frame()获得一帧视频的压缩数据，然后才能对该数据进行解码。
        ret = av_read_frame(avFormatContext, packet);
        if (ret == 0) {
            //如果读取成功，分别添加到音频和视频队列中
            if (videoChannel && packet->stream_index == videoChannel->channelId) {
                videoChannel->pkt_queue.enQueue(packet);
            } else if (audioChannel && packet->stream_index == audioChannel->channelId) {
                audioChannel->pkt_queue.enQueue(packet);
            } else {
                //av_packet_free：将packet指向的数据域的引用技术减1，并将packe的值设为默认值。该函数并不会释
                //放packet的空间，释放不使用的packet需要调用av_packet_free。
                av_packet_free(&packet);
            }
        } else {
            av_packet_free(&packet);
            if (ret == AVERROR_EOF) { //end of file
                //读取完毕，不一定播放完毕
                if (videoChannel->pkt_queue.empty() && videoChannel->frame_queue.empty()
                    && audioChannel->pkt_queue.empty() && audioChannel->frame_queue.empty()) {
                    //播放完毕
                    break;
                }
//            av_usleep(10000);
            } else {
                LOGE("读取数据包失败，返回:%d 错误描述:%s", ret, av_err2str(ret));
                break;
            }
        }
    }

    isPlaying = 0;
    audioChannel->stop();
    videoChannel->stop();
}


void Player::stop() {
    isPlaying = 0;
    pthread_join(prepareTask, 0);
    pthread_join(startTask, 0);

    release();
}

void Player::release() {
    //TODO audioChannel
    if (videoChannel) {
        delete videoChannel;
        videoChannel = 0;
    }
    if (avFormatContext) {
        avformat_close_input(&avFormatContext);
        avformat_free_context(avFormatContext);
        avFormatContext = 0;
    }
}