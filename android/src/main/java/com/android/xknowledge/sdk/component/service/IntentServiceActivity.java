package com.android.xknowledge.sdk.component.service;

import android.content.Intent;
import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * IntentService实践：
 * 参考：https://juejin.cn/post/6844903847027015693
 */
public class IntentServiceActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        findViewById(R.id.intentservice_button_start).setOnClickListener(v -> {
            Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
            Bundle bundle = new Bundle();
            bundle.putString("taskName", "task1");
            intent.putExtras(bundle);
            startService(intent);
        });
    }
}