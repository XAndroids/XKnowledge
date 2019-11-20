package com.android.xknowledge.component.activity.distribute.activity

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.xknowledge.R

class PageBodyModule : BasicModule() {
    private var activity: Activity? = null
    private var parentViewGroupA: ViewGroup? = null
    private var parentViewGroupB: ViewGroup? = null
    private var pageBodyViewA: View? = null
    private var pageBodyViewB: View? = null

    override fun init(moduleContext: ModuleContext, extend: String) {
        activity = moduleContext.getActivity()
        parentViewGroupA = moduleContext.getView(0)
        parentViewGroupB = moduleContext.getView(1)
        this.moduleContext = moduleContext
        initView()
    }

    private fun initView() {
        pageBodyViewA = LayoutInflater.from(activity).inflate(R.layout.module_pagebody_a, parentViewGroupA, true)
        pageBodyViewB = LayoutInflater.from(activity).inflate(R.layout.module_pagebody_b, parentViewGroupB, true)
    }
}
