package com.android.xknowledge.sdk.ui.event.distribute

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * View的事件分发
 */
class EventDistributeActivity : TitleActivity() {
    lateinit var rootView: RootView
    lateinit var view1: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventdistribute)

        rootView = findViewById(R.id.distribute_root_rootview)
        view1 = findViewById(R.id.distribute_root_view1)

        view1.setOnClickListener {
            Log.i(
                "EventDistributeActivity",
                "DistributeActivity_view1_setOnClickListener"
            )
        }

        //返回true，代表已处理事件，故不会在回调onClick，反之false会回调
        view1.setOnTouchListener { _, _ -> false }
    }

    /**
     * 是否向下分发事件
     */
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "DistributeActivity_dispatchTouchEvent,event=${event?.action}"
        )
        return super.dispatchTouchEvent(event)
    }

    /**
     * 是否消费事件
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "DistributeActivity_onTouchEvent"
        )

        val className = Class.forName("android.view.ViewGroup")
        val field = className.getDeclaredField("mFirstTouchTarget")
        field.isAccessible = true
        Log.i("EventDistributeActivity", "${field.get(rootView)}")
        return super.onTouchEvent(event)
    }
}
