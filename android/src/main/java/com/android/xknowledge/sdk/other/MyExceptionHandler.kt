package com.android.xknowledge.sdk.other

import android.content.Context
import android.os.Process
import android.util.Log
import java.lang.Thread.getDefaultUncaughtExceptionHandler

/**
 * 自定义未处理Java异常处理器，捕获异常信息
 * 参考：https://blog.csdn.net/ldxlz224/article/details/101208058
 */
class MyExceptionHandler(private val context: Context) :
    Thread.UncaughtExceptionHandler {
    private var mDefaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    fun init() {
        mDefaultUncaughtExceptionHandler = getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        Log.i(
            "ExitReasonsActivity",
            "thread = " + thread.name + ",throwable = " + throwable.stackTrace
        )

        //捕获异常时，进程没有杀死，此时获取进程退出信息拿不到本次的
        val exitSender = ExitSender()
        exitSender.getAndSendExit(context)

        if (mDefaultUncaughtExceptionHandler != null) {
            mDefaultUncaughtExceptionHandler?.uncaughtException(thread, throwable)
        } else {
            Process.killProcess(Process.myPid())
        }
    }
}