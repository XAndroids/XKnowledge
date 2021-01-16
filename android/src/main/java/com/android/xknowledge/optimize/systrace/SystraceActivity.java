package com.android.xknowledge.optimize.systrace;

import android.os.Bundle;

import androidx.core.os.TraceCompat;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * Systrace API实践
 * FIXME 在Systrace中并没有看到相关的标记
 * 参考：
 * 享学2《性能优化-卡顿和布局优化》
 */
public class SystraceActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TraceCompat.beginSection("SystraceActivity");
        setContentView(R.layout.activity_systrace);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "SystraceThread").start();
        }
        TraceCompat.endSection();
    }
}