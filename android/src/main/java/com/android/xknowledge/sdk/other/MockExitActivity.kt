package com.android.xknowledge.sdk.other

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_mockexit.*

/**
 * MockActivity进程退出
 */
class MockExitActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mockexit)
        //模拟ANR，在主线程执行睡眠
        mockexit_button_anr.setOnClickListener {
            Thread.sleep(15000)
        }
        //模拟Crash
        mockexit_button_crash.setOnClickListener {
            throw NullPointerException()
        }
        //模拟应用自己退出
        mockexit_button_exitself.setOnClickListener {
            System.exit(1024)
        }
        //模拟了OOM，但是还是上传的Crash
        //2020-11-24 15:45:16.288 30577-30577/com.android.xknowledge E/AndroidRuntime: FATAL EXCEPTION: main
        //    Process: com.android.xknowledge, PID: 30577
        //    java.lang.OutOfMemoryError: Failed to allocate a 55380616 byte allocation with 12837816 free bytes and 12MB until OOM, target footprint 201326592, growth limit 201326592
        //        at java.util.Arrays.copyOf(Arrays.java:3136)
        //        at java.util.Arrays.copyOf(Arrays.java:3106)
        //        at java.util.ArrayList.grow(ArrayList.java:275)
        //        at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:249)
        //        at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:241)
        //        at java.util.ArrayList.add(ArrayList.java:467)
        //        at com.android.xknowledge.sdk.other.ExitReasonsActivity$onCreate$4.onClick(ExitReasonsActivity.kt:81)
        //        at android.view.View.performClick(View.java:7448)
        //        at android.view.View.performClickInternal(View.java:7425)
        //        at android.view.View.access$3600(View.java:810)
        //        at android.view.View$PerformClick.run(View.java:28296)
        //        at android.os.Handler.handleCallback(Handler.java:938)
        //        at android.os.Handler.dispatchMessage(Handler.java:99)
        //        at android.os.Looper.loop(Looper.java:223)
        //        at android.app.ActivityThread.main(ActivityThread.java:7656)
        //        at java.lang.reflect.Method.invoke(Native Method)
        //        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592)
        //        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947)
        val stringList = arrayListOf<String>()
        mockexit_button_lowmemory.setOnClickListener {
            for (i in 1..100000) {
                for (j in 1..100000) {
                    stringList.add(String())
                }
            }
        }
    }
}