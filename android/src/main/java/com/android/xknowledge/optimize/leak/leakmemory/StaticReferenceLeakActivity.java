package com.android.xknowledge.optimize.leak.leakmemory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 静态Activity或视图引用
 * 方案：不使用静态引用
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class StaticReferenceLeakActivity extends TitleActivity {
    /*
     * This is a bad idea!
     */
    private static TextView textView;
    private static Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staticreference_leak);

        textView = findViewById(R.id.staticreference_textview_leak);
        textView.setText("Bad Idea!");

        activity = this;
    }
}