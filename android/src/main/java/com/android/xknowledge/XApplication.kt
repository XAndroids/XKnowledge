package com.android.xknowledge

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Process
import android.util.Log
import com.android.xknowledge.optimize.block.blockcanary.BlockCanary
import com.android.xknowledge.optimize.block.choreographer.ChoreographerHelper
import com.android.xknowledge.optimize.crash.CrashReport
import com.android.xknowledge.router.ARouter
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco

class XApplication : Application() {
    companion object {
        var sLeakyActivities = ArrayList<Activity>()
        var isLogin = false
        const val MY_APP_TAG: String = "XApplication";
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.i("XApplication", "attachBaseContext")
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("XApplication", "onCreate")
        //配合多进程，没创建新进程重新执行Application生命周期
        val pid = Process.myPid()
        Log.i("ProcessTestService", "XApplication onCreate,pid = $pid")

        Fresco.initialize(this)
        FLog.setMinimumLoggingLevel(Log.VERBOSE)

//        Fix.fix(classLoader, codeCacheDir.absolutePath, "/storage/emulated/0/fix.dex");

        ARouter.getInstance().init(this);

        //FIXME 为什么我增加了版本控制还是会找不到了崩溃？？
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            val appOpsCallback = object : OnOpNotedCallback() {
//                private fun logPrivateDataAccess(opCode: String, trace: String) {
//                    Log.i(
//                        MY_APP_TAG,
//                        "Private data accessed. Operation: $opCode\nStack Trace:\n$trace"
//                    )
//                }
//
//                override fun onNoted(p0: SyncNotedAppOp) {
//                    logPrivateDataAccess(p0.op, Throwable().stackTrace.toString())
//                }
//
//                override fun onSelfNoted(p0: SyncNotedAppOp) {
//                    logPrivateDataAccess(p0.op, Throwable().stackTrace.toString())
//                }
//
//                override fun onAsyncNoted(p0: AsyncNotedAppOp) {
//                    logPrivateDataAccess(p0.op, p0.message)
//                }
//            }
//
//            val appOpsManager = getSystemService(AppOpsManager::class.java) as AppOpsManager
//            appOpsManager.setOnOpNotedCallback(mainExecutor, appOpsCallback)
//        }

        //卡顿检测
        BlockCanary.install()
        ChoreographerHelper.start()

        //捕获Java异常
        CrashReport.init(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.i("XApplication", "onTrimMemory,level = $level");
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.i("XApplication", "onTrimMemory")
    }
}