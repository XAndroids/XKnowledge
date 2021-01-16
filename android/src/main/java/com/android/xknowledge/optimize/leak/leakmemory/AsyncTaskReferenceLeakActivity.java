package com.android.xknowledge.optimize.leak.leakmemory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.lang.ref.WeakReference;

/**
 * AsyncTask引用
 * 方案：1.使用静态内部类+弱引用；2.Destroy 取消任务
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class AsyncTaskReferenceLeakActivity extends TitleActivity {
    private TextView textView;
    private BackgroundTask backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctaskreference_leak);

        /*
         * Executing AsyncTask here!
         * */
        backgroundTask = new BackgroundTask(textView);
        backgroundTask.execute();
    }

    /*
     * 解决方案1：使用静态内部类
     * */
//    private static class BackgroundTask extends AsyncTask<Void, Void, String> {
    private class BackgroundTask extends AsyncTask<Void, Void, String> {

        private final WeakReference<TextView> messageViewReference;

        private BackgroundTask(TextView textView) {
            this.messageViewReference = new WeakReference<>(textView);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "The task is completed!";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            /*
             * Fix number 3
             * */
            TextView textView = messageViewReference.get();
            if (textView != null) {
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
         * 解决方案2：在Activity onDestroy()时取消异步任务
         * */
        if (backgroundTask != null) {
            backgroundTask.cancel(true);
        }
    }
}