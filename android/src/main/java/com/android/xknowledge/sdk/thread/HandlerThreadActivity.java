package com.android.xknowledge.sdk.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * HandlerThread实践
 * 参考：https://blog.csdn.net/carson_ho/article/details/79285760
 */
public class HandlerThreadActivity extends TitleActivity {
    //HandlerThread子线程
    HandlerThread handlerThread = new HandlerThread("work Thread");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
        handlerThread.start();
        Handler workHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("HandlerThreadActivity", "thread = " + Thread.currentThread()
                        + "msg = " + msg.toString());
            }
        };

        findViewById(R.id.handlerthread_button_sendmessage).setOnClickListener(v -> {
            //从主线程发送消息给子线程，在Looper中回调handleMessage执行
            Message message = Message.obtain();
            message.what = 2;
            message.obj = "B";
            workHandler.sendMessage(message);
        });

        findViewById(R.id.handlerthread_button_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出线程handler之后，在发送消息报错如下
                //2019-12-17 20:07:36.853 23727-23727/com.android.xknowledge W/MessageQueue: Handler (com.android.xknowledge.sdk.thread.HandlerThreadActivity$1) {fa0ab4c} sending message to a Handler on a dead thread
                //    java.lang.IllegalStateException: Handler (com.android.xknowledge.sdk.thread.HandlerThreadActivity$1) {fa0ab4c} sending message to a Handler on a dead thread
                //        at android.os.MessageQueue.enqueueMessage(MessageQueue.java:558)
                //        at android.os.Handler.enqueueMessage(Handler.java:754)
                //        at android.os.Handler.sendMessageAtTime(Handler.java:703)
                //        at android.os.Handler.sendMessageDelayed(Handler.java:673)
                //        at android.os.Handler.sendMessage(Handler.java:611)
                //        at com.android.xknowledge.sdk.thread.HandlerThreadActivity.lambda$onCreate$0(HandlerThreadActivity.java:37)
                //        at com.android.xknowledge.sdk.thread.-$$Lambda$HandlerThreadActivity$0Qx21loI6993kMvhrTAj6DUiaEY.onClick(Unknown Source:2)
                //        at android.view.View.performClick(View.java:7140)
                //        at android.view.View.performClickInternal(View.java:7117)
                //        at android.view.View.access$3500(View.java:801)
                //        at android.view.View$PerformClick.run(View.java:27351)
                //        at android.os.Handler.handleCallback(Handler.java:883)
                //        at android.os.Handler.dispatchMessage(Handler.java:100)
                //        at android.os.Looper.loop(Looper.java:214)
                //        at android.app.ActivityThread.main(ActivityThread.java:7356)
                //        at java.lang.reflect.Method.invoke(Native Method)
                //        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
                //        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
                handlerThread.quit();
            }
        });
    }
}