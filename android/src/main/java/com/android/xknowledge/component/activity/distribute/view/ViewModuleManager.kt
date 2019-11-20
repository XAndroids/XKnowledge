package com.android.xknowledge.component.activity.distribute.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.SparseArrayCompat
import com.android.xknowledge.component.activity.distribute.activity.ModuleContext
import com.android.xknowledge.component.activity.distribute.activity.ModuleFactory
import com.android.xknowledge.component.activity.distribute.activity.ModuleManager
import com.android.xknowledge.component.activity.distribute.activity.ModuleUtil

class ViewModuleManager : ModuleManager() {

    fun initModules(saveInstance: Bundle?, activity: Activity?, rootView: View, modules: ArrayMap<String, List<Int>>?) {
        if (activity == null || modules == null) {
            return
        }

        this.modules = modules
        initModules(saveInstance, activity, rootView)
    }

    private fun initModules(saveIntanceState: Bundle?, activity: Activity, rootView: View) {
        //获取配置
        for (moduleName in this.modules.keys) {
            if (ModuleUtil.empty(moduleName)) {
                return
            }
            getPool().execute {
                val module = ModuleFactory.newModuleInstance(moduleName)
                if (module != null) {
                    activity.runOnUiThread{
                        val moduleContext = ModuleContext()
                        moduleContext.setActivity(activity)
                        moduleContext.setSaveInstance(saveIntanceState)

                        //关联视图
                        val sVerticalViews = SparseArrayCompat<ViewGroup>()
                        val viewIds = this.modules[moduleName]
                        if (viewIds != null && viewIds.isNotEmpty()) {
                            for (i in viewIds.indices) {
                                sVerticalViews.put(i, rootView.findViewById<View>(viewIds[i]) as ViewGroup)
                            }
                        }

                        moduleContext.setViewGroups(sVerticalViews)
                        module.init(moduleContext, "")

                        allModules[moduleName] = module
                    }
                }
            }
        }
    }
}
