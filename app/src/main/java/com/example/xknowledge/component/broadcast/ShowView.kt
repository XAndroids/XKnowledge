package com.example.xknowledge.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.xknowledge.R

class ShowView : LinearLayout {
    var approveCount: Int = 0
    var opposeCount: Int = 0

    private val approveView: TextView
    val opposeView: TextView

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.show_view, this)

        approveView = findViewById(R.id.showview_approve_textview)
        opposeView = findViewById(R.id.showview_oppose_textview)
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                approveCount++
                approveView.text =
                    resources.getString(R.string.showview_approve_text, approveCount)
            }

        }, IntentFilter("APPROVE_ACTION"))


        localBroadcastManager.registerReceiver(
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