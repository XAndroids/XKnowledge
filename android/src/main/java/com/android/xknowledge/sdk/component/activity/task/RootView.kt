package com.android.xknowledge.sdk.component.activity.task

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import java.util.*


class RootView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    init {
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                val activityClassName: String = getActivity()?.javaClass?.name ?: ""
                Log.i("RootView", "activityClassName = $activityClassName")
                val topActivityClassName = getTopActivity(context)
                Log.i("RootView", "topActivityClassName = $topActivityClassName")
            }
        }
        Timer().schedule(timerTask, 0, 1000)
    }

    private fun getActivity(): Activity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    fun getTopActivity(context: Context): String? {
        val am =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list = am.getRunningTasks(1)
        if (list != null && list.size > 0) {
            return list[0].topActivity?.className
        }
        return ""
    }
}