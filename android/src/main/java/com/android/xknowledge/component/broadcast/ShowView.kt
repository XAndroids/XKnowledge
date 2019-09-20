package com.android.xknowledge.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.xknowledge.R

class ShowView : LinearLayout {
    var approveCount: Int = 0
    var opposeCount: Int = 0

    val approveView: TextView
    val opposeView: TextView

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.show_view, this)

        approveView = findViewById(R.id.showview_approve_textview)
        opposeView = findViewById(R.id.showview_oppose_textview)
        //本地广播，只有本app发送的广播
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                approveCount++
                approveView.text =
                    resources.getString(R.string.showview_approve_text, approveCount)
            }

        }, IntentFilter("APPROVE_ACTION"))

        //全局广播，可以接受系统全局的广播
        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    opposeCount++
                    opposeView.text =
                        resources.getString(R.string.showview_oppose_text, opposeCount)
                }
            },
            IntentFilter("OPPOSE_ACTION")
        )
    }
}