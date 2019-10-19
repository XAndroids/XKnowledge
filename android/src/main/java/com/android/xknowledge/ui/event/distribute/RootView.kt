package com.android.xknowledge.ui.event.distribute

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.RelativeLayout

class RootView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    /**
     * 是否向下分发事件
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "RootView_dispatchTouchEventevent"
        )
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 是否拦截时间自己处理
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "RootView_onInterceptTouchEvent"
        )
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 是否自己消费事件
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "RootView_onTouchEvent"
        )
        return super.onTouchEvent(event)
    }
}