package com.android.xknowledge.tool

import android.app.ActivityManager
import android.content.Context

/**
 * Activity栈相关工具方法
 */
object TaskTools {
    /**
     * 获取当前应用Activity栈顶Activity名称
     */
    fun geStaskToptActivity(context: Context): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfoList = activityManager.getRunningTasks(1)
        return runningTaskInfoList[0].topActivity!!.className
    }
}