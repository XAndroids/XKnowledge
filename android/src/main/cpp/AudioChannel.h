//
// Created by QITMAC0000562 on 2021/1/2.
//

#ifndef XKNOWLEDGE_AUDIOCHANNEL_H
#define XKNOWLEDGE_AUDIOCHANNEL_H


#include "BaseChannel.h"

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

extern "C" {
#include <libswresample/swresample.h>
#include <libavcodec/avcodec.h>
};

class AudioChannel : public BaseChannel {
public:
    AudioChannel(int channelId, JavaCallHelper *helper, AVCodecContext *avCodecContext,
                 const AVRational &base);

    virtual ~AudioChannel();

public:
    void _play();

    int _getData();

    void _releaseOpenSL();
public:
    virtual void play();

    virtual void decode();

    virtual void stop();

public:
    uint8_t *buffer;

private:
    pthread_t audioDecodeTask, audioPlayTask;
    SwrContext *swrContext = 0;
    int bufferCount;
    int out_channls;
    int out_sampleSize;

    SLObjectItf engineObject = NULL;
    SLEngineItf engineInterface = NULL;
    SLObjectItf outputMixObject = NULL;
    SLObjectItf bqPlayerObject = NULL;
    SLAndroidSimpleBufferQueueItf bqPlayerBufferQueue = NULL;
    SLPlayItf bqPlayerInterface = NULL;
};


#endif //XKNOWLEDGE_AUDIOCHANNEL_H
