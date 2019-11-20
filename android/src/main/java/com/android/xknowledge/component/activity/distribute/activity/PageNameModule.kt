package com.android.xknowledge.component.activity.distribute.activity

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.xknowledge.R

class PageNameModule : BasicModule() {
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
        pageNameView = LayoutInflater.from(activity).inflate(R.layout.page_name_layout, parentViewGroup, true)
    }
}
