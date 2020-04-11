package com.android.xknowledge

import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco


class XApplication : Application() {
    companion object {
        var sLeakyActivities = ArrayList<Activity>()
        var isLogin = false
    }


    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        FLog.setMinimumLoggingLevel(Log.VERBOSE)
    }
}