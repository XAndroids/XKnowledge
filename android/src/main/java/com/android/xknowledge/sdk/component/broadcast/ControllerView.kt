package com.android.xknowledge.sdk.component.broadcast

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.xknowledge.R

/**
 * 发送普通、顺序、粘性、本地广播
 */
class ControllerView : LinearLayout {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.controller_view, this)

        findViewById<Button>(R.id.controllerview_normal_button).setOnClickListener {
            //发送普通广播，所有广播接受者同时接收到数据
            val intent = Intent("Glocal_Receiver")
            intent.putExtra("data", "normal");
            context.sendBroadcast(intent)
        }

        findViewById<Button>(R.id.controllerview_ordered_button).setOnClickListener {
            //发送有序广播，接受者按照优先级先后接收数据，并且可以修改或中断广播
            val intent = Intent("Glocal_Receiver")
            intent.putExtra("data", "ordered");
            context.sendOrderedBroadcast(intent, null)
        }

        findViewById<Button>(R.id.controllerview_local_button).setOnClickListener {
            //发送本地广播，只有本app能接收数据
            val intent = Intent("Local_Receiver")
            intent.putExtra("data", "local")
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }

        findViewById<Button>(R.id.controllerview_sticky_button).setOnClickListener {
            //发送粘性广播，发送后注册也可以接受到
            val intent = Intent("Stickly_Receiver")
            intent.putExtra("data", "stickly")
            context.sendStickyBroadcast(intent)
        }
    }
}