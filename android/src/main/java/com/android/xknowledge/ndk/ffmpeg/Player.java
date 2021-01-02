package com.android.xknowledge.ndk.ffmpeg;

import android.view.Surface;

/**
 * 播放器类，调用底层ndk相关方法实现视频播放
 */
public class Player {
    static {
        //加载封装的ffmpeg ndk库
        System.loadLibrary("native-lib");
    }

    private final long nativeHandle;
    private OnPrepareListener onPrepareListener;
    private OnProgressListener onProgressListener;
    private OnErrorListener onErrorListener;

    public void setOnPrepareListener(OnPrepareListener onPrepareListener) {
        this.onPrepareListener = onPrepareListener;
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public Player() {
        //构造方法，返回Native底层的C++ Play对象，调用setDataSource/prepare等方法时再传递如ndk用于后续调用
        nativeHandle = nativeInit();
    }

    public void setDataSource(String path) {
        setDataSource(nativeHandle, path);
    }

    public void setSurface(Surface surface) {
        setSurface(nativeHandle, surface);
    }

    public void prepare() {
        prepare(nativeHandle);
    }

    public void start() {
        start(nativeHandle);
    }

    public void stop() {
        stop(nativeHandle);
    }

    //Java调用Native方法
    private native long nativeInit();

    private native void setDataSource(long nativeHandle, String path);

    private native void setSurface(long nativeHandle, Surface surface);

    private native void prepare(long nativeHandle);

    private native void start(long nativeHandle);

    private native void stop(long nativeHandle);

    //Native调用Java方法，onError、onPrepare和onProgress
    private void onError(int errorCode) {
        if (null != onErrorListener) {
            onErrorListener.onError(errorCode);
        }
    }

    private void onPrepare() {
        if (null != onPrepareListener) {
            onPrepareListener.onPrepared();
        }
    }

    private void onProgress(int progress) {
        if (null != onProgressListener) {
            onProgressListener.onProgress(progress);
        }
    }

    //Java 回调接口返回Ndk回调的相关信息
    public interface OnPrepareListener {
        void onPrepared();
    }

    public interface OnProgressListener {
        void onProgress(int progress);
    }

    public interface OnErrorListener {
        void onError(int err);
    }
}
