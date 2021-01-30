package com.android.xknowledge.sdk.process;

import android.content.Intent;
import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * Android多进程实践
 * 参考：https://blog.csdn.net/lixpjita39/article/details/77435156
 */
public class MutilProcessActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil_process);

        findViewById(R.id.process_button_startprivate).setOnClickListener(v -> {
            Intent intent = new Intent(MutilProcessActivity.this, PrivateProcessService.class);
            startService(intent);
        });

        findViewById(R.id.process_button_startpublic).setOnClickListener(v -> {
            Intent intent = new Intent(MutilProcessActivity.this, PublicProcessService.class);
            startService(intent);
        });
    }
}