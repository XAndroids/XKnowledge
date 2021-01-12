package com.android.xknowledge.optimize.leakmemory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 广播未注销
 * 方案：onDestroy注销广播
 * 参考：https://android.jlelse.eu/9-ways-to-avoid-memory-leaks-in-android-b6d81648e35e
 */
public class BroadcastReceiverLeakActivity extends TitleActivity {
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver_leak);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadCastReceiver();
    }

    private void registerBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //your receiver code goes here!
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("SmsMessage.intent.MAIN"));
    }

    @Override
    protected void onStop() {
        super.onStop();

        /*
         * 解决方案
         * Uncomment this line in order to avoid memory leak.
         * You need to unregister the broadcast receiver since the broadcast receiver keeps a
         * reference of the activity. Now when its time for your Activity to die, the Android
         * framework will call onDestroy() on it but the garbage collector will not be able to
         * remove the instance from memory because the broadcastReceiver is still holding a
         * strong reference to it.
         * */
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}