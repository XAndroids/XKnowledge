package com.android.xknowledge.sdk.component.activity.distribute.activity

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat

/**
 * 模块上下文，保存了模块需要保存的Activity，saveInstance和viewGroups信息
 */
class ModuleContext {
    private var component: Activity? = null
    private var saveInstance: Bundle? = null
    private var viewGroups: SparseArrayCompat<ViewGroup> = SparseArrayCompat()

    fun getActivity(): Activity? {
        return component
    }

    fun setActivity(component: Activity?) {
        this.component = component
    }

    fun setSaveInstance(saveInstance: Bundle?) {
        this.saveInstance = saveInstance
    }

    fun getView(key: Int): ViewGroup? {
        return viewGroups.get(key)
    }

    fun setViewGroups(viewGroups: SparseArrayCompat<ViewGroup>) {
        this.viewGroups = viewGroups
    }
}