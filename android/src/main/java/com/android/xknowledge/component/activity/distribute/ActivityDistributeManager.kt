package com.android.xknowledge.component.activity.distribute

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.SparseArrayCompat

class ActivityDistributeManager : DistributeManager() {

    fun initModules(saveInstance: Bundle?, activity: Activity?, modules: ArrayMap<String, List<Int>>?) {
        if (activity == null || modules == null) {
            return
        }
        moduleConfig(modules)
        initModules(saveInstance, activity)
    }

    private fun initModules(saveInstance: Bundle?, activity: Activity) {
        //获取配置
        for (moduleName in modules.keys) {
            getPool().execute {
                val module = ModuleFactory.newModuleInstance(moduleName)
                Log.d(TAG, "ActivityDistributeManager init module name: $moduleName")
                if (module != null) {
                    val moduleContext = ModuleContext()
                    moduleContext.setActivity(activity)
                    moduleContext.setSaveInstance(saveInstance)

                    //关联视图
                    val viewGroups = SparseArrayCompat<ViewGroup>()
                    val mViewIds = modules[moduleName]
                    if (mViewIds != null && mViewIds.isNotEmpty()) {
                        for (i in mViewIds.indices) {
                            viewGroups.put(i, activity.findViewById<View>(mViewIds[i]) as ViewGroup)
                        }
                    }

                    moduleContext.setViewGroups(viewGroups)

                    activity.runOnUiThread{
                        module.init(moduleContext, "")
                        allModules[moduleName] = module
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "ActivityDistribute"
    }
}
