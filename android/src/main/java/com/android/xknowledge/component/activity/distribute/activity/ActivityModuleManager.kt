package com.android.xknowledge.component.activity.distribute.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.SparseArrayCompat

/**
 * Activity分发模块管理类，负责Activity分发模块的初始化
 */
class ActivityModuleManager : ModuleManager() {

    fun initModules(saveInstance: Bundle?, activity: Activity?, modules: ArrayMap<String, List<Int>>?) {
        if (activity == null || modules == null) {
            return
        }
        this.modules = modules
        initModules(saveInstance, activity)
    }

    private fun initModules(saveInstance: Bundle?, activity: Activity) {
        //获取配置
        for (moduleName in this.modules.keys) {
            getPool().execute {
                //反射创建模块，解耦了Activity和模块的耦合，可以跨组件分发
                val module =
                    ModuleFactory.newModuleInstance(
                        moduleName
                    )
                if (module != null) {
                    //关联上下文、saveInstance、视图
                    val moduleContext =
                        ModuleContext()
                    moduleContext.setActivity(activity)
                    moduleContext.setSaveInstance(saveInstance)
                    val viewGroups = SparseArrayCompat<ViewGroup>()
                    val mViewIds = this.modules[moduleName]
                    if (mViewIds != null && mViewIds.isNotEmpty()) {
                        for (i in mViewIds.indices) {
                            viewGroups.put(i, activity.findViewById<View>(mViewIds[i]) as ViewGroup)
                        }
                    }
                    moduleContext.setViewGroups(viewGroups)

                    //在UI线程初始化模块
                    activity.runOnUiThread{
                        module.init(moduleContext, "")
                        allModules[moduleName] = module
                    }
                }
            }
        }
    }
}
