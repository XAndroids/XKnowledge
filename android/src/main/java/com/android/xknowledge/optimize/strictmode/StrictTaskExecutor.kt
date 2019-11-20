package com.android.xknowledge.optimize.strictmode

import android.os.SystemClock
import android.os.StrictMode.noteSlowCall


class StrictTaskExecutor {
    fun executeTask(task: Runnable) {
        val startTime = SystemClock.uptimeMillis()
        task.run()
        val cost = SystemClock.uptimeMillis() - startTime
        //自己监测超时，然后手动记录SlowCall日志
        if (cost > SLOW_CALL_THRESHOLD) {
            noteSlowCall("slowCall cost=$cost")
        }
    }

    companion object {
        private val SLOW_CALL_THRESHOLD: Long = 500
    }
}