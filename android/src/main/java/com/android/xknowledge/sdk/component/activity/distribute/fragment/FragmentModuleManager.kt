package com.android.xknowledge.sdk.component.activity.distribute.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.SparseArrayCompat
import androidx.fragment.app.FragmentActivity
import com.android.xknowledge.sdk.component.activity.distribute.activity.ModuleContext
import com.android.xknowledge.sdk.component.activity.distribute.activity.ModuleFactory
import com.android.xknowledge.sdk.component.activity.distribute.activity.ModuleManager
import com.android.xknowledge.sdk.component.activity.distribute.activity.ModuleUtil

class FragmentModuleManager : ModuleManager() {
    fun initModules(savedInstanceState: Bundle?, activity: FragmentActivity, rootView: View, moduleConfig: ArrayMap<String, List<Int>>) {
        this.modules = moduleConfig
        initModules(savedInstanceState, activity, rootView)
    }

    private fun initModules(savedInstanceState: Bundle?, activity: FragmentActivity?, rootView: View){
        for (moduleName in modules.keys) {
            if (ModuleUtil.empty(moduleName)) {
                return
            }
            getPool().execute {
                //创建模块
                val module = ModuleFactory.newModuleInstance(moduleName)
                if (module != null) {
                    val moduleContext = ModuleContext()
                    //关联Activity
                    moduleContext.setActivity(activity)
                    moduleContext.setSaveInstance(savedInstanceState)

                    //关联视图
                    val sVerticalViews = SparseArrayCompat<ViewGroup>()
                    val viewIds = this.modules[moduleName]
                    if (viewIds != null && viewIds.isNotEmpty()) {
                        for (i in viewIds.indices) {
                            sVerticalViews.put(i, rootView.findViewById<View>(viewIds[i]) as ViewGroup)
                        }
                    }
                    moduleContext.setViewGroups(sVerticalViews)

                    activity?.runOnUiThread{
                        module.init(moduleContext, "")
                        allModules[moduleName] = module
                    }
                }
            }

        }
    }
}
