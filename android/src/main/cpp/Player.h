//
// Created by QITMAC0000562 on 2021/1/1.
//

#ifndef XKNOWLEDGE_PLAYER_H
#define XKNOWLEDGE_PLAYER_H

#include <pthread.h>

extern "C" {
#include <libavformat/avformat.h>
}

#include "JavaCallHelper.h"
#include "VideoChannel.h"
#include "AudioChannel.h"

/**
 * 播放者类Player
 */
class Player {
    //友元函数，可以访问类的私有成员
    friend void *prepare_t(void *args);

    friend void *start_t(void *args);

//公共函数
public:
    //构造函数
    Player(JavaCallHelper *helper);

    //析构函数
    ~Player();

    //设置视频资源路径
    void setDataSource(const char *path);

    //设置绘制视频的Window
    void setWindow(ANativeWindow *window);

    //执行视频播放准备工作
    void prepare();

    //开始播放视频
    void start();

    //停止播放视频
    void stop();

    //释放视频播放资源
    void release();

//私有函数
private:
    void _prepare();

    void _start();

//私有成员变量
private:
    //播放视频资源的路径
    char *path;
    int64_t duration;
    bool isPlaying;

    //视频播放准备工作线程，开始播放视频线程
    pthread_t prepareTask, startTask;

    //回调Java方法帮助类
    JavaCallHelper *helper;

    VideoChannel *videoChannel = 0;
    AudioChannel *audioChannel = 0;

    //媒体文件构成和基本信息上下文
    AVFormatContext *avFormatContext = 0;
    ANativeWindow *window = 0;
};


#endif //XKNOWLEDGE_PLAYER_H
