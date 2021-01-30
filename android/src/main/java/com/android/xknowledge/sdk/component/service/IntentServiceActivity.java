package com.android.xknowledge.sdk.component.service;

import android.content.Intent;
import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * IntentService实践：
 * //可连续启动Service，顺序执行后台服务，完成后自动关闭
 * 2019-12-17 22:57:10.373 27130-27130/com.android.xknowledge I/MyIntentService: onCreate,thread = Thread[main,5,main]
 * 2019-12-17 22:57:10.375 27130-27130/com.android.xknowledge I/MyIntentService: onStartCommand,thread = Thread[main,5,main]
 * 2019-12-17 22:57:10.375 27130-27260/com.android.xknowledge I/MyIntentService: onHandleIntent,thread = Thread[IntentService[MyIntentService],5,main]
 * 2019-12-17 22:57:11.123 27130-27166/com.android.xknowledge D/LeakCanary: Setting up flushing for Thread[IntentService[MyIntentService],5,main]
 * 2019-12-17 22:57:11.219 27130-27130/com.android.xknowledge I/MyIntentService: onStartCommand,thread = Thread[main,5,main]
 * 2019-12-17 22:57:13.376 27130-27260/com.android.xknowledge I/MyIntentService: do task1
 * 2019-12-17 22:57:13.384 27130-27260/com.android.xknowledge I/MyIntentService: onHandleIntent,thread = Thread[IntentService[MyIntentService],5,main]
 * 2019-12-17 22:57:16.387 27130-27260/com.android.xknowledge I/MyIntentService: do task1
 * 2019-12-17 22:57:16.396 27130-27130/com.android.xknowledge I/MyIntentService: onDestroy
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