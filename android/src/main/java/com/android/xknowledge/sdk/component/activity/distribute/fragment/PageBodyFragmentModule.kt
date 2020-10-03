package com.android.xknowledge.sdk.component.activity.distribute.fragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.xknowledge.R
import com.android.xknowledge.sdk.component.activity.distribute.activity.BasicModule
import com.android.xknowledge.sdk.component.activity.distribute.activity.ModuleContext

class PageBodyFragmentModule : BasicModule() {
    private var activity: Activity? = null
    private var parentViewGroupA: ViewGroup? = null
    private var parentViewGroupB: ViewGroup? = null
    private var pageBodyFragmentA: View? = null
    private var pageBodyFragmentB: View? = null

    override fun init(moduleContext: ModuleContext, extend: String) {
        activity = moduleContext.getActivity()
        parentViewGroupA = moduleContext.getView(0)
        parentViewGroupB = moduleContext.getView(1)
        this.moduleContext = moduleContext
        initView()
    }

    private fun initView() {
        pageBodyFragmentA = LayoutInflater.from(activity).inflate(R.layout.module_pagebody_fragmneta, parentViewGroupA, true)
        pageBodyFragmentB = LayoutInflater.from(activity).inflate(R.layout.module_pagebod_fragmentb, parentViewGroupB, true)
    }
}