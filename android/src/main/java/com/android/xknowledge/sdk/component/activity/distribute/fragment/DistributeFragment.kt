package com.android.xknowledge.sdk.component.activity.distribute.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap
import com.android.xknowledge.R
import com.android.xknowledge.sdk.component.activity.distribute.activity.PageConfig
import com.android.xknowledge.sdk.component.activity.distribute.view.ModuleManagerView
import java.util.ArrayList

/**
 * 分发Fragment，负责Fragment要分发模块的配置信息创建
 */
class DistributeFragment : ModuleManagerFragment() {
    private lateinit var moduleManagerView: ModuleManagerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_distribute, container, false)
        if(!this::moduleManagerView.isInitialized){
            moduleManagerView = object : ModuleManagerView(activity,savedInstanceState,view.findViewById<ViewGroup>(R.id.distributeview_relativelayout_pageview)){
                override fun moduleConfig(): ArrayMap<String, List<Int>> {
                    val map = ArrayMap<String, List<Int>>()
                    map[PageConfig.MODULE_VIEW_PAGE_NAME] = object : ArrayList<Int>() {init { add(R.id.distributeview_relativelayout_pageviewname) } }
                    return map
                }
            }
        }
        return view
    }

    override fun moduleConfig(): ArrayMap<String, List<Int>> {
        val map = ArrayMap<String, List<Int>>()
        map[PageConfig.MODULE_PAGE_NAME] = object : ArrayList<Int>() {init { add(R.id.distributefragment_relativelayout_pagename) } }
        map[PageConfig.MODULE_BODY_BT_NAME] = object : ArrayList<Int>() {
            init {
                add(R.id.distributefragment_relativelayout_pagebodyA)
                add(R.id.distributefragment_relativelayout_pagebodyB)
            }
        }
        return map
    }

    override fun onResume() {
        super.onResume()
        if(this::moduleManagerView.isInitialized){
            moduleManagerView.onResume()
        }
    }

    override fun onStop() {
        super.onStop()
        if(this::moduleManagerView.isInitialized){
            moduleManagerView.onStop()
        }

    }
}
