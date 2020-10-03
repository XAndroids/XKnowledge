package com.android.xknowledge.sdk.component.activity.distribute.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap

/**
 * 模块管理Activity，负责分发模块的初始化和模块的具体分发
 */
abstract class ModuleManagerActivity : AppCompatActivity() {
    private lateinit var activityModuleManager: ActivityModuleManager

    abstract fun moduleConfig(): ArrayMap<String, List<Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            if (!this::activityModuleManager.isInitialized) {
                activityModuleManager =
                    ActivityModuleManager()
                activityModuleManager.initModules(savedInstanceState, this@ModuleManagerActivity, moduleConfig())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (this::activityModuleManager.isInitialized) {
            activityModuleManager.onResume()
        }
    }

    override fun onStop() {
        super.onStop()
        if (this::activityModuleManager.isInitialized) {
            activityModuleManager.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::activityModuleManager.isInitialized) {
            activityModuleManager.onDestroy()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (this::activityModuleManager.isInitialized) {
            activityModuleManager.onConfigurationChanged(newConfig)
        }
    }
}