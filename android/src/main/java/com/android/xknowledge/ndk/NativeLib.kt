package com.android.xknowledge.ndk

class NativeLib {
    //一个由'native-lib'Native库实现的Native方法，它和这个应用一起打包
    external fun stringFromJNI(): String

    external fun writeTest(): Void

    external fun readTest(): Void
}