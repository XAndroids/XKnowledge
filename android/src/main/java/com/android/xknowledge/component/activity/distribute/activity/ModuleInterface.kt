package com.android.xknowledge.component.activity.distribute.activity

import android.os.Bundle

/**
 * 模块基类，要被分发到模块的方法
 */
interface ModuleInterface {
    fun init(moduleContext: ModuleContext, extend: String)

    fun onSaveInstanceState(outState: Bundle)

    fun onResume()

    fun onPause()

    fun onStop()

    fun onOrientationChanges(isLandscape: Boolean)

    fun onDestroy()
}
