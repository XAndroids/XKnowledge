package com.android.xknowledge.optimize.leak.leakcanary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.android.xknowledge.R;

/**
 * 异步任务未执行完，造成内存谢罗
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 100000);
    }
}
