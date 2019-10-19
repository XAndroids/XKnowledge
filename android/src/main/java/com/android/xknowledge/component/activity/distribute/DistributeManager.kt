package com.android.xknowledge.component.activity.distribute

import android.content.res.Configuration
import android.os.Handler
import androidx.collection.ArrayMap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


open class DistributeManager {
    var modules: ArrayMap<String, List<Int>> = ArrayMap()
    protected var allModules: ArrayMap<String, AbsModule> = ArrayMap()

    private lateinit var pool: ExecutorService
    private lateinit var handler: Handler

    fun getPool(): ExecutorService {
        if (!this::pool.isInitialized) {
            pool = Executors.newSingleThreadExecutor()
        }
        return pool
    }

    fun getHandler(): Handler {
        if (!this::handler.isInitialized){
            handler = Handler()
        }
        return handler
    }

    fun moduleConfig(modules: ArrayMap<String, List<Int>>) {
        this.modules = modules
    }

    fun getModuleByNames(name: String): AbsModule? {
        return if (!ModuleUtil.empty(allModules)) allModules[name] else null
    }

    fun onResume() {
        for (module in allModules.values)
            module?.onResume()
    }

    fun onPause() {
        for (module in allModules.values)
            module?.onPause()
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
