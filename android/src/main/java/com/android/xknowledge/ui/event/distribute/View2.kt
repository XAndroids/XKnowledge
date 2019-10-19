package com.android.xknowledge.ui.event.distribute

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class View2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    /**
     * 是否向下分发事件
     */
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.i(
            "DistributeMainActivity",
            "View2_dispatchTouchEvent"
        )
        return super.dispatchTouchEvent(event)
    }

    /**
     * 是否自己消费事件
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(
            "DistributeMainActivity",
            "View2_onTouchEvent"
        )
        return super.onTouchEvent(event)
    }
}