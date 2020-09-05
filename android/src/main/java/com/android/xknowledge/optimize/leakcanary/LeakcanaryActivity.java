package com.android.xknowledge.optimize.leakcanary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.xknowledge.R;

/**
 * Leakcanary的使用和原理
 * 参考：https://juejin.im/post/6844903924747468813
 */
public class LeakcanaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leakcanary);
        findViewById(R.id.leakcanary_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeakcanaryActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }
}
