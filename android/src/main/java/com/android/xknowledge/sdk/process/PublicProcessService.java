package com.android.xknowledge.sdk.process;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 公开进程
 */
public class PublicProcessService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        //和PrivateProcessService，不在同一个进程，故访问的静态变量也会不同
        Log.i("ProcessTestService", "PublicProcessService onCreate,processFlag =" +
                PrivateProcessService.processFlag);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("ProcessTestService", "PublicProcessService onBind,processFlag =" +
                PrivateProcessService.processFlag);
        return null;
    }
}
