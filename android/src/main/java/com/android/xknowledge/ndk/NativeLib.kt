package com.android.xknowledge.ndk

class NativeLib {
    //一个由'native-lib'Native库实现的Native方法，它和这个应用一起打包
    external fun stringFromJNI(): String

//    external fun getFFmpegVersion(): Int

//    external fun writeTest(): Void
//
//    external fun readTest(): Void

    companion object {
        init {
            //运行时加载原生库
            System.loadLibrary("native-lib")
        }
    }
}