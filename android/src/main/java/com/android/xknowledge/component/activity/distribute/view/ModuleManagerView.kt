package com.android.xknowledge.component.activity.distribute.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.collection.ArrayMap
import androidx.fragment.app.FragmentActivity

abstract class ModuleManagerView : View {
    private lateinit var viewModuleManager: ViewModuleManager

    abstract fun moduleConfig(): ArrayMap<String, List<Int>>

    constructor(context: Context) : super(context)

    constructor(context: Context?, savedInstanceState: Bundle?, rootView: View) : super(context) {
        if (!::viewModuleManager.isInitialized) {
            viewModuleManager = ViewModuleManager()
            viewModuleManager.initModules(savedInstanceState, context as FragmentActivity, rootView, this.moduleConfig())
        }
    }

    fun onStop() {
        if (this::viewModuleManager.isInitialized) {
            viewModuleManager.onStop()
        }
    }

    fun onResume() {
        if (this::viewModuleManager.isInitialized) {
            viewModuleManager.onResume()
        }
    }

    fun onDestroy() {
        if (this::viewModuleManager.isInitialized) {
            viewModuleManager.onDestroy()
        }
    }

    override fun onDetachedFromWindow() {
        viewModuleManager.onDestroy()
        super.onDetachedFromWindow()
    }
}