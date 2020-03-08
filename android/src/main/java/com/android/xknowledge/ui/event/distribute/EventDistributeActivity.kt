package com.android.xknowledge.ui.event.distribute

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class EventDistributeActivity : TitleActivity() {
    lateinit var rootView: RootView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventdistribute)

        rootView = findViewById(R.id.distribute_root_rootview)
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