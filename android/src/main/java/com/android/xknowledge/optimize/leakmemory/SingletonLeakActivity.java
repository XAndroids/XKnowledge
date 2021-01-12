package com.android.xknowledge.optimize.leakmemory;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 单例类持有Context
 * 方案：1.使用ApplicationContext；2.onDestroy Context= null
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class SingletonLeakActivity extends TitleActivity {
    private SingletonSampleClass singletonSampleClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_leak);
        /*
         * 解决方案1: Do not pass activity context to the Singleton class. Instead pass application
         * Context
         */
        singletonSampleClass = SingletonSampleClass.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
         * 解决方案2: Unregister the singleton class here i.e. if you pass activity context to the
         * Singleton class,then ensure that when the activity is destroyed, the context in the
         * singleton class is set to null.
         */
        singletonSampleClass.onDestroy();
    }
}