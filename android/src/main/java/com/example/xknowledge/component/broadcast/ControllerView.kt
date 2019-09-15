package com.example.xknowledge.component.broadcast

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.xknowledge.R

class ControllerView : LinearLayout {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.controller_view, this)

        findViewById<Button>(R.id.controllerview_approve_button).setOnClickListener {
            //只有本app可以收到
            LocalBroadcastManager.getInstance(context).sendBroadcast(Intent("APPROVE_ACTION"))
        }

        findViewById<Button>(R.id.controllerview_oppose_button).setOnClickListener {
            //系统全局都可以收到
            context.sendBroadcast(Intent("OPPOSE_ACTION"))
        }
    }
}