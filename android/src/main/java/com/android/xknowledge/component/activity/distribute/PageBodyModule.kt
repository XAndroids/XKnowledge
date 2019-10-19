package com.android.xknowledge.component.activity.distribute

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.xknowledge.R

class PageBodyModule : BasicModule() {
    private var activity: Activity? = null
    private var parentViewGroup_T: ViewGroup? = null
    private var parentViewGroup_B: ViewGroup? = null
    private var pageBodyView_fi: View? = null
    private var pageBodyView_se: View? = null
    private var pageBodyTop: TextView? = null
    private var pageBodyBottom: TextView? = null
    private var changeNameBtn: Button? = null

    override fun init(moduleContext: ModuleContext, extend: String) {
        activity = moduleContext.getActivity()
        parentViewGroup_T = moduleContext.getView(0)
        parentViewGroup_B = moduleContext.getView(1)
        this.moduleContext = moduleContext
        initView()
    }

    private fun initView() {
        pageBodyView_fi = LayoutInflater.from(activity).inflate(R.layout.page_body_fi, parentViewGroup_T, true)
        pageBodyTop = pageBodyView_fi!!.findViewById(R.id.page_body_top) as TextView

        pageBodyView_se = LayoutInflater.from(activity).inflate(R.layout.page_body_se, parentViewGroup_B, true)
        pageBodyBottom = pageBodyView_se!!.findViewById(R.id.page_body_bottom) as TextView
        changeNameBtn = pageBodyView_se!!.findViewById(R.id.change_page_Name) as Button
    }
}
