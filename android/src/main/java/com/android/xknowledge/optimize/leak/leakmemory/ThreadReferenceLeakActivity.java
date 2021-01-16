package com.android.xknowledge.optimize.leak.leakmemory;

import android.os.Bundle;
import android.util.Log;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 线程耗时任务
 * 方案：1.非静态成员；2.静态内部类；3.onDestroy()停止线程
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class ThreadReferenceLeakActivity extends TitleActivity {
    /*
     * Mistake Number 1: Do not use static variables
     * */
//    private static LeakyThread thread;

    //FIXME 为什么改了非静态成员，也会有内存泄露
    //解决方案1：非静态成员
    private LeakyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadreference_leak);

        createThread();
    }

    private void createThread() {
        thread = new LeakyThread();
        thread.start();
    }

    /*
     * Mistake Number 2: Non-static anonymous classes hold an
     * implicit reference to their enclosing class.
     * */
//    private class LeakyThread extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//            }
//        }
//    }

    /*
     * 解决方案2：使用静态内部类
     * */
//    private static class LeakyThread extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解决方案3：终止线程
        thread.stop = false;
    }

    private class LeakyThread extends Thread {
        public boolean stop = true;

        @Override
        public void run() {
            while (stop) {
                Log.i("Leak", "LeakyThread");
            }
        }
    }
}