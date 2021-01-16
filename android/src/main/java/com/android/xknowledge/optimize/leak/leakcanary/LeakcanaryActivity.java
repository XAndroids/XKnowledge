package com.android.xknowledge.optimize.leak.leakcanary;

import android.content.Intent;
import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * Leakcanary的使用和原理
 * 参考：https://juejin.im/post/6844903924747468813
 */
public class LeakcanaryActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leakcanary);
        findViewById(R.id.leakcanary_button).setOnClickListener(v -> {
            Intent intent = new Intent(LeakcanaryActivity.this, TestActivity.class);
            startActivity(intent);
        });

    }
}
