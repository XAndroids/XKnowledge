package com.android.xknowledge.component.activity.distribute.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.xknowledge.R
import com.android.xknowledge.component.activity.distribute.activity.BasicModule
import com.android.xknowledge.component.activity.distribute.activity.ModuleContext

class PageViewModule : BasicModule() {
    private var activity: Activity? = null
    private var parentViewGroup: ViewGroup? = null
    private var pageNameView: View? = null

    override fun init(moduleContext: ModuleContext, extend: String) {
        activity = moduleContext.getActivity()
        parentViewGroup = moduleContext.getView(0)
        this.moduleContext = moduleContext
        initView()
    }

    private fun initView() {
        pageNameView = LayoutInflater.from(activity).inflate(R.layout.module_pageview, parentViewGroup, true)
    }
}
