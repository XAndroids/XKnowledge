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

        findViewById(R.id.handler_button_postrunnable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FIXME 为什么postRunnable不生效？？？
                //从主线程postRunnable，在子线程Looper中执行
                workHandler.post(() -> Log.i("HandlerThreadActivity", "thread = " + Thread
                        .currentThread()));

            }
        });

        findViewById(R.id.handlerthread_button_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.quit();
            }
        });
    }
}