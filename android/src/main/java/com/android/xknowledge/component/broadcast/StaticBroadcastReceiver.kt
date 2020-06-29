package com.android.xknowledge.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StaticBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //接受静态广播
        val msg = intent!!.getStringExtra("msg")
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}