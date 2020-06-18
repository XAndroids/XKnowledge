package com.android.xknowledge.thread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * Handler线程通信
 */
class HandlerActivity : TitleActivity() {
    //Handler匿名内部类内存泄露，匿名Hander被Looper引用，匿名Hander持有HandlerActivity，Looper生命周期整个应用
    //参考：https://www.jianshu.com/p/b699be7268b5
    private val handlerMain: MainHander = MainHander()
    private lateinit var handlerThread: ThreadHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        Thread(Runnable {
            //1.子线程向主线程发送消息
            val message = Message()
            message.what = 0
            message.obj = "thread to main message"
            handlerMain.sendMessage(message)
        }).start()

        Thread(Runnable {
            Looper.prepare()
            handlerThread = ThreadHandler(Looper.myLooper())

            //2.子线程向子线程发送消息
            val message = Message()
            message.what = 1
            message.obj = "thread to thread message"
            handlerThread.sendMessage(message)

            Looper.loop()
        }).start()

        //等在子线程handlerThread初始化
        Thread.sleep(2000)

        //3.主线程向子线程发送消息
        val message = Message()
        message.what = 2
        message.obj = "main to thread message"
        handlerThread.sendMessage(message)
    }

    //Kotlin静态内部类
    //参考：https://kotlinlang.org/docs/reference/nested-classes.html
    class MainHander : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.i(
                "HandlerActivity",
                "current thread ${Thread.currentThread().name} , msg.what = ${msg.what} , msg.obj = ${msg.obj}"
            )
        }
    }

    class ThreadHandler(myLooper: Looper?) : Handler(myLooper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.i(
                "HandlerActivity",
                "current thread ${Thread.currentThread().name} , msg.what = ${msg.what} , msg.obj = ${msg.obj}"
            )
        }
    }
}
