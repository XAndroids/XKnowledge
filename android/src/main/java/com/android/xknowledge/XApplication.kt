package com.android.xknowledge

import android.app.Activity
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco


class XApplication : Application() {
    companion object {
        var sLeakyActivities = ArrayList<Activity>()
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}