package com.android.xknowledge.sdk.component.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        Log.i("MyIntentService", "onCreate,thread = " + Thread.currentThread());
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("MyIntentService", "onStartCommand,thread = " + Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("MyIntentService", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("MyIntentService", "onHandleIntent,thread = " + Thread.currentThread());
        String taskName = intent.getExtras().getString("taskName");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (taskName) {
            case "task1":
                Log.i("MyIntentService", "do task1");
                break;
            case "task2":
                Log.i("MyIntentService", "do task2");
                break;
            default:
                break;
        }
    }
}
