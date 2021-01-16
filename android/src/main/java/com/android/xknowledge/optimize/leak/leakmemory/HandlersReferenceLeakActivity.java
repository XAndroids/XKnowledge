package com.android.xknowledge.optimize.leak.leakmemory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.lang.ref.WeakReference;

/**
 * Handler引用：Message中的callback和target，包含Handler和Runnable引用，如果Handler和Runnable是内部类，则会
 * 简介引用Activity造成内存泄露
 * 方案：
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class HandlersReferenceLeakActivity extends TitleActivity {
    private TextView textView;

    /*
     * Mistake Number 1
     * */
//    private Handler leakyHandler = new Handler();

    private MyHandler myHandler = new MyHandler();
    private MyRunnable myRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlersreference_leak);
        textView = findViewById(R.id.handlersreferenceleak_textview_leak);

        /*
         * Mistake Number 2
         * */
//        leakyHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText(getString(R.string.app_name));
//            }
//        }, 50000);

        myRunnable = new MyRunnable(this);
        myHandler.postDelayed(myRunnable, 50000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解决方案2：Activity onDestroy移除Runnable，MyRunnable不需要静态内部类！！！
//        myHandler.removeCallbacks(myRunnable);
    }

    /*
     * 解决方案1：Handler修改为静态内部类，因为Handler Message的target持有Handler实例，避免非静态内部
     * 类持有外部类引用，造成内存泄露
     */
    private static class MyHandler extends Handler {
        public MyHandler() {
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    /*
     * 解决方案1：MyRunnable修改为静态内部类，因为Handler Message的callback只有MyRunnable实例，避免非静态内部
     * 类持有外部类引用，造成内存泄露
     * */
    private static class MyRunnable implements Runnable {

        private WeakReference<HandlersReferenceLeakActivity> weakReference;

        public MyRunnable(HandlersReferenceLeakActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            HandlersReferenceLeakActivity activity = weakReference.get();
            if (activity != null) {
                activity.textView.setText(activity.getString(R.string.app_name));
            }
        }
    }
}