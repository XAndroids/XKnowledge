package com.android.xknowledge.sdk.component.activity.distribute.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment

abstract class ModuleManagerFragment : Fragment() {
    private lateinit var fragmentModuleManager: FragmentModuleManager

    abstract fun moduleConfig(): ArrayMap<String, List<Int>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentModuleManager = FragmentModuleManager()
        fragmentModuleManager.initModules(savedInstanceState, activity!!, view, moduleConfig())
    }

    override fun onResume() {
        super.onResume()
        if (this::fragmentModuleManager.isInitialized) {
            fragmentModuleManager.onResume()
        }
    }

    override fun onStop() {
        super.onStop()
        if(this::fragmentModuleManager.isInitialized){
            fragmentModuleManager.onStop()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(this::fragmentModuleManager.isInitialized){
            fragmentModuleManager.onDestroy()
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(this::fragmentModuleManager.isInitialized){
            fragmentModuleManager.onConfigurationChanged(newConfig!!)
        }
    }
}
