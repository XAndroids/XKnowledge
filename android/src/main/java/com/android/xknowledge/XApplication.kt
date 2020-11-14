package com.android.xknowledge

import android.app.*
import android.app.AppOpsManager.OnOpNotedCallback
import android.os.Build
import android.util.Log
import com.android.xknowledge.framework.hotfix.Fix
import com.android.xknowledge.router.ARouter
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco

class XApplication : Application() {
    companion object {
        var sLeakyActivities = ArrayList<Activity>()
        var isLogin = false
        const val MY_APP_TAG: String = "XApplication";
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        FLog.setMinimumLoggingLevel(Log.VERBOSE)

        Fix.fix(classLoader, codeCacheDir.absolutePath, "/storage/emulated/0/fix.dex");

        ARouter.getInstance().init(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val appOpsCallback = object : OnOpNotedCallback() {
                private fun logPrivateDataAccess(opCode: String, trace: String) {
                    Log.i(
                        MY_APP_TAG,
                        "Private data accessed. Operation: $opCode\nStack Trace:\n$trace"
                    )
                }

                override fun onNoted(p0: SyncNotedAppOp) {
                    logPrivateDataAccess(p0.op, Throwable().stackTrace.toString())
                }

                override fun onSelfNoted(p0: SyncNotedAppOp) {
                    logPrivateDataAccess(p0.op, Throwable().stackTrace.toString())
                }

                override fun onAsyncNoted(p0: AsyncNotedAppOp) {
                    logPrivateDataAccess(p0.op, p0.message)
                }
            }

            val appOpsManager = getSystemService(AppOpsManager::class.java) as AppOpsManager
            appOpsManager.setOnOpNotedCallback(mainExecutor, appOpsCallback)
        }
    }
}