package com.android.xknowledge.sdk.process;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 私有进程
 */
public class PrivateProcessService extends Service {
    public static boolean processFlag = false;

    @Override
    public void onCreate() {
        super.onCreate();
        processFlag = true;
        Log.i("ProcessTestService", "PrivateProcessService onCreate,processFlag = "
                + processFlag);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("ProcessTestService", "PrivateProcessService onBind");
        return null;
    }
}
