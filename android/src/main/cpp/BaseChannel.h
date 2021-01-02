//
// Created by QITMAC0000562 on 2021/1/2.
//

#ifndef XKNOWLEDGE_BASECHANNEL_H
#define XKNOWLEDGE_BASECHANNEL_H

#include "safe_queue.h"
#include "JavaCallHelper.h"

extern "C" {
#include <libavformat/avformat.h>
}

class BaseChannel {
public:
    BaseChannel(int channelId, JavaCallHelper *helper, AVCodecContext *avCodecContext, AVRational
    base) : channelId(channelId),
            helper(helper),
            avCodecContext(avCodecContext),
            time_base(base) {
        pkt_queue.setReleaseHandle(releaseAvPacket);
        frame_queue.setReleaseHandle(releaseAvFrame);
    }

    void setEnable(bool enable) {
        pkt_queue.setEnable(enable);
        frame_queue.setEnable(enable);
    }

    static void releaseAvFrame(AVFrame *&frame) {
        if (frame) {
            av_frame_free(&frame);
            frame = 0;
        }
    }

    static void releaseAvPacket(AVPacket *&packet) {
        if (packet) {
            av_packet_free(&packet);
            packet = 0;
        }
    }

public:
    int channelId;
    JavaCallHelper *helper;
    AVCodecContext *avCodecContext;
    AVRational time_base;

    //视频帧播放集合
    SafeQueue<AVPacket *> pkt_queue;
    //视频帧集合
    SafeQueue<AVFrame *> frame_queue;
    bool isPlaying = false;

    double clock = 0;
};


#endif //XKNOWLEDGE_BASECHANNEL_H
