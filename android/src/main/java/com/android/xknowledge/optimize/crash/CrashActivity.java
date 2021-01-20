package com.android.xknowledge.optimize.crash;

import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 异常监控实践
 * 参考：享学2《性能优化-Crash处理》
 */
public class CrashActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        findViewById(R.id.crash_button_java).setOnClickListener(v -> {
            CrashReport.testJavaCrash();
        });

        findViewById(R.id.crash_button_native).setOnClickListener(v -> {
            CrashReport.testNativeCrash();
        });
    }
}