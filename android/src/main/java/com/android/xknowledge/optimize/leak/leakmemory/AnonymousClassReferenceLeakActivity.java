package com.android.xknowledge.optimize.leak.leakmemory;

import android.os.Bundle;
import android.widget.TextView;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.lang.ref.WeakReference;

/**
 * 匿名类引用
 * 方案：匿名类static并且使用弱引用
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class AnonymousClassReferenceLeakActivity extends TitleActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymousclassreference_leak);

        textView = findViewById(R.id.anonymousclassreference_textview_leak);
        textView.setText(getString(R.string.app_name));

        /*
         * Runnable class is defined here
         * */
        new Thread(new LeakyRunnable(textView)).start();
    }

    /**
     * 解决方案：使用静态内部类，不持有外部类Activity的引用
     */
    private static class LeakyRunnable implements Runnable {

        private final WeakReference<TextView> messageViewReference;

        private LeakyRunnable(TextView textView) {
            this.messageViewReference = new WeakReference<>(textView);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TextView textView = messageViewReference.get();
            if (textView != null) {
//                textView.setText("Runnable class has completed its work");
            }
        }
    }
}