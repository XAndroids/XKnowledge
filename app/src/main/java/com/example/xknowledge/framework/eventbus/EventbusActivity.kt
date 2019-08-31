package com.example.xknowledge.framework.eventbus

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventbusActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventbus)

        findViewById<Button>(R.id.eventbus_postmainthread_button).setOnClickListener {
            EventBus.getDefault().post(MessageEvent("Post on MainThread!"))
            //由于handleSomethingElse处理了耗时任务，导致改Log延迟发布
            Log.i("EventbusActivity", "main post after")
        }

        findViewById<Button>(R.id.eventbus_postchildthread_button).setOnClickListener {
            Thread(Runnable {
                EventBus.getDefault().post(MessageEvent("Post on ChildThread"))
                Log.i("EventbusActivity", "child post after")
            }).start()
        }
    }

    override fun onStart() {
        super.onStart()
        //观察者也需要从事件总线注册和注销自己，当观察者被注册的时候，它们将会获取事件
        //在Android中，在Activity和Fragment通常在它们的生命周期中注册
        //大多数情况下，onStart/onStop比较合适
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

//    //默认的ThreadMode.PSTING，和发送线程在同一个线程执行
//    //事件同步完成，一旦发布完成，所有接受者都调用
//    @Subscribe
//    fun onMessageEvent(event: MessageEvent) {
//        Log.i(
//            "EventbusActivity",
//            "onMessageEvent," + event.message + ",Thread = ${Thread.currentThread()}"
//        )
//    }
//
//    @Subscribe
//    fun onMessageEvent2(event: MessageEvent) {
//        //发布线程在主线程，即订阅者调用也是在main线程
//        Log.i(
//            "EventbusActivity",
//            "onMessageEvent2," + event.message + ",Thread = ${Thread.currentThread()}"
//        )
//        //模拟耗时操作，阻塞发布线程main，"阻塞"main,无法运行
//        Thread.sleep(3000)
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onMessageEvent3(event: MessageEvent) {
//        //无论发布线程是什么线程，都在主线程
//        Log.i(
//            "EventbusActivity",
//            "onMessageEvent3," + event.message + ",Thread = ${Thread.currentThread()}"
//        )
//
//        //模拟耗时操作，阻塞发布线程main，"阻塞"main，无法运行
//        Thread.sleep(3000)
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
//    fun onMessageEvent4(event: MessageEvent) {
//        //MAIN_ORDERED，发送MessageEvent2事件，先执行onMessageEvent5，然后在执行本身
//        EventBus.getDefault().post(MessageEvent2("Post on onMessageEvent4"))
//
//        //总是等待后面的订阅者，或者post执行完毕后，在执行
//        Log.i(
//            "EventbusActivity",
//            "onMessageEvent4," + event.message + ",Thread = ${Thread.currentThread()}"
//        )
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onMessageEvent5(event: MessageEvent2) {
//        Log.i(
//            "EventbusActivity",
//            "onMessageEvent5," + event.message + ",Thread = ${Thread.currentThread()}"
//        )
//    }

//    //如果发送线程不是主线程，就在该线程执行
//    //如果发送线程是主线程，则创建线程池子线程执行
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    fun onMessageEvent6(event: MessageEvent) {
//        Log.i(
//            "EventbusActivity",
//            "onMessageEvent6," + event.message + ",Thread = ${Thread.currentThread()}"
//        )
//
//        //如果是子线程发布，则模拟阻塞子线程
//        Thread.sleep(30000)
//    }

    //始终保持独立线程运行
    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onMessageEvent7(event: MessageEvent) {
        Log.i(
            "EventbusActivity",
            "onMessageEvent7," + event.message + ",Thread = ${Thread.currentThread()}"
        )

        //如果是子线程发布，则模拟阻塞子线程
        Thread.sleep(30000)
    }
}
