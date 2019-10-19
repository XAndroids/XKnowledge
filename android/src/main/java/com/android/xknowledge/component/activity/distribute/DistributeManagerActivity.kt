package com.android.xknowledge.component.activity.distribute

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap

abstract class DistributeManagerActivity : AppCompatActivity() {
    private lateinit var distributeManager: ActivityDistributeManager

    abstract fun moduleConfig(): ArrayMap<String, List<Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            if (!this::distributeManager.isInitialized) {
                val ti = System.currentTimeMillis()
                distributeManager = ActivityDistributeManager()
                distributeManager.initModules(savedInstanceState, this@DistributeManagerActivity, moduleConfig())
                Log.v("ModuleManageActivity", "init use time = " + (System.currentTimeMillis() - ti))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (this::distributeManager.isInitialized) {
            distributeManager.onResume()
        }

    }

    override fun onStop() {
        super.onStop()
        if (this::distributeManager.isInitialized) {
            distributeManager.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::distributeManager.isInitialized) {
            distributeManager.onDestroy()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (this::distributeManager.isInitialized) {
            distributeManager.onConfigurationChanged(newConfig)
        }
    }
}