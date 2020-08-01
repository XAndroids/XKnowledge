package com.android.xknowledge

import android.app.Activity
import android.app.Application
import android.util.Log
import com.android.xknowledge.framework.hotfix.Fix
import com.android.xknowledge.router.ARouter
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

        Fix.fix(classLoader, codeCacheDir.absolutePath, "/storage/emulated/0/fix.dex");

        ARouter.getInstance().init(this);
    }
}