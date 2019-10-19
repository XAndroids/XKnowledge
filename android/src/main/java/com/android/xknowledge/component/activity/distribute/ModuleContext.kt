package com.android.xknowledge.component.activity.distribute

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat

class ModuleContext {
    private var component: Activity? = null
    private var saveInstance: Bundle? = null
    private var viewGroups: SparseArrayCompat<ViewGroup> = SparseArrayCompat()

    fun getActivity(): Activity? {
        return component
    }

    fun setActivity(component: Activity) {
        this.component = component
    }

    fun getSaveInstance(): Bundle? {
        return saveInstance
    }

    fun setSaveInstance(saveInstance: Bundle?) {
        this.saveInstance = saveInstance
    }

    fun getView(key: Int): ViewGroup? {
        return viewGroups.get(key)
    }

    fun getViewGroups(): SparseArrayCompat<ViewGroup> {
        return viewGroups
    }

    fun setViewGroups(viewGroups: SparseArrayCompat<ViewGroup>) {
        this.viewGroups = viewGroups
    }
}