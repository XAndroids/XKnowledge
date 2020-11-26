package com.android.xknowledge.sdk.other

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.util.Log
import java.util.*

class ExitSender {
    fun getAndSendExit(context: Context): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            lastUploadTimestamp = context.getSharedPreferences("ExitSender", 0).getLong(
                LAST_UPLOAD_TIMESTAMP, 0
            )

            //package：可空的参数，指定你想获取哪个应用退出的原因，传递null为当前应用；获取其它包名需要dump全新，不提供给第三方应用
            //pid：这个包名进程退出前的进程ID。0忽略这个参数返回所有匹配的记录；
            //maxNum：返回结果的最大数量，0忽略这个参数返回所有匹配的记录；
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val exitReasons = activityManager.getHistoricalProcessExitReasons(null, 0, 0)
            //ApplicationExitInfo(
            // timestamp=2020/6/3 上午6:55 进程退出的时间，从1970...开始
            // pid=4006 终止进程的进程id
            // realUid=10234 进程的内核用户标识符，系统用来进行权限控制
            // packageUid=10234 和realUid类型，在软件安装时分配的内核用户标识
            // definingUid=10234 内存用户标识，可能和realUid/packageUid不同
            // user=0 在多用户系统上返回用户ID
            // process=com.android.xknowledge 运行时实际的进程名称
            // reason=10 (USER REQUESTED) 进程退出的原因
            //  6-ANR，应用无响应
            //  4-Crash，未捕获的Java异常
            //  5-Crash，Native
            //  12-进程依赖的依赖消失，如内容提供者kill，则client也会退出
            //  9-因为过度使用资源，被系统杀了
            //  1-程序进程自己退出，如通过System.exit()，getstatus返回退出code
            //  7-进程初始化失败而终止，如启动过程附加到系统时间太长，初始化期间发生错误
            //  3-因为系统内存过低(不是应用OOM，应用OOM属于Crash)，被系统杀了，不是所有的设备都支持REASON_LOW_MEMORY。如果不支持getreason会返回reason_signaled，getstatus返回SIGKILL，isLowMemoryKillReportSupported()可判断是否支持；
            //  13-进程由于各种其他原因而被系统杀死，这些原因不是应用程序中的问题，也不是应用程序可操作的，如系统刚刚完成升级，description中会描述具体原因;
            //  8-因为运行时权限改变退出
            //  12-进程因为系统信号退出，android.system.OsConstants#SIGKILL
            //  0-未知原因
            //  10-用户主动杀进程、
            //  11-因为在多用户的设备上运行它导致的退出；
            // status=0 如果应用调用了exit()则会传递的状态参数，否则是应用发出了信号量
            // importance=100  进程终止前的重要性(100-前台、200-屏幕前端，没有焦点...等），https://developer.android.com/reference/kotlin/android/app/ActivityManager.RunningAppProcessInfo?hl=zh-cn#importance_background
            //   IMPORTANCE_BACKGROUND/IMPORTANCE_EMPTY/IMPORTANCE_CACHED-400：后台（后台无任何感知）
            //   IMPORTANCE_CANT_SAVE_STATE：
            //   IMPORTANCE_FOREGROUND-100：前台，可获取焦点
            //   IMPORTANCE_FOREGROUND_SERVICE-100：前台Service
            //   IMPORTANCE_GONE-1000：进程退出
            //   IMPORTANCE_PERCEPTIBLE-230：不直接使用，但是可感知(后台播放音乐可感知)
            //   IMPORTANCE_PERCEPTIBLE_PRE_26-130：
            //   IMPORTANCE_SERVICE-300：后台Service
            //   IMPORTANCE_TOP_SLEEPING/IMPORTANCE_TOP_SLEEPING_PRE_28-325：前台，但系统休眠，用于无法看见
            //   IMPORTANCE_VISIBLE-200：前台，无法获取焦点（弹窗/对话框后面）
            // pss=65MB  进程独占内存+按比例共享内存，这是进程上次采样值，不是死亡之前的确切内存信息；如果进程在系统有机会获取样本之前终止，则该值为零
            // rss=108MB 进程独占内存+共享内存，这是进程上次采样值，不是死亡之前的确切内存信息；如果进程在系统有机会获取样本之前终止，则该值为零，https://www.jianshu.com/p/9edfe9d5eb34
            // description=stop com.android.xknowledge due to from pid 4188//系统返回的易于理解的进程终止原因，可以为null
            // state=empty
            // trace=null  返回进程终止之前的堆栈流，通常只在原因是REASON_ANR时有用，进款进程获取ARN又恢复了，并在以后因为其他原因死亡，但trace让然会在ApplictionExitInfo中（一致都保留吗？）
            Collections.sort(exitReasons, ExitComparator())
            val result = StringBuffer()
            for ((index, applicationExitInfo) in exitReasons.withIndex()) {
                result.append("第{$index}个:").append(applicationExitInfo.toString()).append("\n")
                if (applicationExitInfo.timestamp > lastUploadTimestamp) {
                    Log.i("ExitReasonsActivity", applicationExitInfo.timestamp.toString());
                    lastUploadTimestamp = applicationExitInfo.timestamp;
                    val sharedPreferences = context.getSharedPreferences("ExitSender", 0)
                    with(sharedPreferences.edit()) {
                        putLong(LAST_UPLOAD_TIMESTAMP, lastUploadTimestamp)
                        commit()
                    }
                }
            }
            return result.toString()
        }
        return ""
    }

    companion object {
        const val LAST_UPLOAD_TIMESTAMP = "lastUploadTimestamp"
        //上一次上传退出原因的时间
        var lastUploadTimestamp: Long = 0
    }
}