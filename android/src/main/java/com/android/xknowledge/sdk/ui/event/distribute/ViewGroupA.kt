package com.android.xknowledge.sdk.ui.event.distribute

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class ViewGroupA(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    /**
     * 是否向下分发事件
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "ViewGroupA_dispatchTouchEventevent"
        )
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 是否拦截时间自己处理
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "ViewGroupA_onInterceptTouchEvent"
        )
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 是否自己消费事件
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(
            "EventDistributeActivity",
            "ViewGroupA_onTouchEvent"
        )
        return super.onTouchEvent(event)
    }
}