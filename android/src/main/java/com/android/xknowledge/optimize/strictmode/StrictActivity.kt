package com.android.xknowledge.optimize.strictmode

import android.os.Bundle
import android.os.Environment
import android.os.StrictMode.*
import android.view.View
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.android.xknowledge.XApplication
import java.io.*

//参考：https://droidyue.com/blog/2015/09/26/android-tuning-tool-strictmode/
//FIXME 打开开发者选项-严格模式已启用，并没有出现屏幕的闪烁
class StrictActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //开启严格模式，ThreadPolicy和VmPolicy检测违例
        //ThreadPolicy：
        //  自定义的耗时调用 使用detectCustomSlowCalls()开启
        //  磁盘读取操作 使用detectDiskReads()开启
        //  磁盘写入操作 使用detectDiskWrites()开启
        //  网络操作 使用detectNetwork()开启
        setThreadPolicy(
            ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()//输出日志
                .build()
        )
        //虚拟机策略检测的内容有
        //Activity泄露 使用detectActivityLeaks()开启
        //  未关闭的Closable对象泄露 使用detectLeakedClosableObjects()开启
        //  泄露的Sqlite对象 使用detectLeakedSqlLiteObjects()开启
        //  检测实例数量 使用setClassInstanceLimit()开启
        setVmPolicy(
            VmPolicy.Builder()
                .setClassInstanceLimit(StrictActivity::class.java, 1)//自定义检测类实例泄露
//                .detectActivityLeaks()
                .detectLeakedSqlLiteObjects()//或者.detectAll()监测所有问题
                .detectLeakedClosableObjects()
//                .penaltyDeath()//杀死App
                .penaltyLog()
                .build()
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strict)
    }

    fun writeToExternalStorage(view: View) {
        //2019-11-20 16:00:46.767 2953-2953/com.android.xknowledge W/System.err:     at com.android.xknowledge.optimize.strictmode.StrictActivity.writeToExternalStorage(StrictActivity.kt:53)
        //2019-11-20 16:00:46.778 2953-2953/com.android.xknowledge D/StrictMode: StrictMode policy violation; ~duration=2 ms: android.os.strictmode.DiskWriteViolation
        //        at android.os.StrictMode$AndroidBlockGuardPolicy.onWriteToDisk(StrictMode.java:1460)
        //        at java.io.FileOutputStream.<init>(FileOutputStream.java:236)
        //        at com.android.xknowledge.optimize.strictmode.StrictActivity.writeToExternalStorage(StrictActivity.kt:53)
        //        at java.lang.reflect.Method.invoke(Native Method)
        //        at androidx.appcompat.app.AppCompatViewInflater$DeclaredOnClickListener.onClick(AppCompatViewInflater.java:385)
        val externalStorage = Environment.getExternalStorageDirectory()
        val destFile = File(externalStorage, "dest.txt")
        try {
            val outPutStream = FileOutputStream(destFile, true)
            outPutStream.write("droidyue.com".toByteArray())
            outPutStream.flush()
            outPutStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun readFromExternalStorage(view: View) {
        val externalStorage = Environment.getExternalStorageDirectory()
        val destFile = File(externalStorage, "dest.txt")
        try {
            val intPutStream = FileInputStream(destFile)
            val readBytes = ByteArray(1024)
            intPutStream.read(readBytes)
            intPutStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun activityLeak(view: View) {
        //2019-11-20 16:00:47.136 2953-2953/com.android.xknowledge D/StrictMode: StrictMode policy violation: android.os.strictmode.InstanceCountViolation: class com.android.xknowledge.optimize.strictmode.StrictActivity; instances=8; limit=1
        //at android.os.StrictMode.setClassInstanceLimit(StrictMode.java:1)
        XApplication.sLeakyActivities.add(this)
    }

    fun customSlowCalls(view: View) {
        val executor = StrictTaskExecutor()
        executor.executeTask(Runnable {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })
    }
}
