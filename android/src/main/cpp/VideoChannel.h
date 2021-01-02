//
// Created by QITMAC0000562 on 2021/1/2.
//

#ifndef XKNOWLEDGE_VIDEOCHANNEL_H
#define XKNOWLEDGE_VIDEOCHANNEL_H

#include <android/native_window.h>

#include "BaseChannel.h"
#include "AudioChannel.h"

/**
 * 视频播放类：VideoChannel
 */
class VideoChannel : public BaseChannel {
    friend void *videoPlay_t(void *args);

public:
    VideoChannel(int channelId, JavaCallHelper *helper, AVCodecContext *avCodecContext,
                 const AVRational &base, double fps);

    virtual  ~VideoChannel();

    void setWindow(ANativeWindow *window);

public:
    //开始播放视频
    virtual void play();

    //解码视频
    virtual void decode();

    //停止播放视频
    virtual void stop();

private:
    void _play();

    void _onDraw(uint8_t *data[4], int linesize[4], int width, int height);

private:
    double fps;
    pthread_mutex_t surfaceMutex;
    //视频编码和播放子线程
    pthread_t videoDecodeTask, videoPlayTask;
    bool isPlaying;
    ANativeWindow *window = 0;
public:
    AudioChannel *audioChannel = 0;
};


#endif //XKNOWLEDGE_VIDEOCHANNEL_H
