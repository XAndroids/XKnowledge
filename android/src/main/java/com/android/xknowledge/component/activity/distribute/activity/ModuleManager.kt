package com.android.xknowledge.component.activity.distribute.activity

import android.content.res.Configuration
import androidx.collection.ArrayMap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 模块管理类，负责模块信息和初始化模块的管理
 */
open class ModuleManager {
    //配置的模块
    var modules: ArrayMap<String, List<Int>> = ArrayMap()
    //初始化之后的模块
    protected var allModules: ArrayMap<String, ModuleInterface> = ArrayMap()
    //线程池，执行异步反射初始化模块操作
    private lateinit var pool: ExecutorService

    fun getPool(): ExecutorService {
        if (!this::pool.isInitialized) {
            pool = Executors.newSingleThreadExecutor()
        }
        return pool
    }

    fun onResume() {
        for (module in allModules.values)
            module?.onResume()
    }

    fun onStop() {
        for (module in allModules.values)
            module?.onStop()
    }

    fun onConfigurationChanged(newConfig: Configuration) {
        for (module in allModules.values)
            module?.onOrientationChanges(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
    }

    fun onDestroy() {
        for (module in allModules.values)
            module?.onDestroy()
    }
}
