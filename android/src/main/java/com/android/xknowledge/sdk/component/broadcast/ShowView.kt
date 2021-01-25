package com.android.xknowledge.sdk.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.xknowledge.R

/**
 * 接受普通、有序、本地和粘性广播
 */
class ShowView : LinearLayout {
    val globalView1: TextView
    val globalView2: TextView
    val globalView3: TextView
    val localView: TextView
    val sticklyView: TextView
    val sticklyButton: Button

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.show_view, this)

        globalView1 = findViewById(R.id.showview_global1_textview)
        globalView2 = findViewById(R.id.showview_global2_textview)
        globalView3 = findViewById(R.id.showview_global3_textview)
        localView = findViewById(R.id.showview_local_textview)
        sticklyView = findViewById(R.id.showview_stickly_textview)
        sticklyButton = findViewById(R.id.showview_stickly_button)

        sticklyButton.setOnClickListener(OnClickListener {
            //粘性广播，点击注册也可以接收以前发送的广播
            context.registerReceiver(
                object : BroadcastReceiver() {
                    override fun onReceive(context: Context?, intent: Intent?) {
                        val data = intent?.getStringExtra("data")
                        sticklyView.text = data
                    }
                },
                IntentFilter("Stickly_Receiver")
            )
        })

        //全局广播1，静态注册广播，设置优先级，如果是有序广播添加数据
        val intentFilter = IntentFilter("Glocal_Receiver");
        intentFilter.priority = 1000
        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    val data = intent?.getStringExtra("data")
                    globalView1.text = data

                    if (isOrderedBroadcast) {
                        resultData = data + "receive1"
                    }
                }
            },
            intentFilter
        )
        //全局广播2，如果是有序广播中断广播
        val intentFilter2 = IntentFilter("Glocal_Receiver");
        intentFilter.priority = 2000
        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    if (!isOrderedBroadcast) {
                        val data = intent?.getStringExtra("data")
                        globalView2.text = data
                    } else {
                        globalView2.text = resultData;
                        abortBroadcast();
                    }
                }
            },
            intentFilter2
        )

        //全局广播3，如果是有序广播收不到
        val intentFilter3 = IntentFilter("Glocal_Receiver");
        intentFilter.priority = 3000
        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    val data = intent?.getStringExtra("data")
                    globalView3.text = data
                }
            },
            intentFilter3
        )

        //本地广播，只有本app发送的广播
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                localView.text = intent?.getStringExtra("data")
            }

        }, IntentFilter("Local_Receiver"))
    }
}