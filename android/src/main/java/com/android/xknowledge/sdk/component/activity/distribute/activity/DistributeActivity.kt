package com.android.xknowledge.sdk.component.activity.distribute.activity

import android.os.Bundle
import androidx.collection.ArrayMap
import com.android.xknowledge.R
import com.android.xknowledge.sdk.component.activity.distribute.fragment.DistributeFragment

/**
 * 分发Activity
 */
class DistributeActivity : ModuleManagerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distribute)
        //分发Fragment
        supportFragmentManager.beginTransaction().replace(R.id.distribute_framelayout_container,
            DistributeFragment()
        ).commit()
    }

    override fun moduleConfig(): ArrayMap<String, List<Int>> {
        //需要从Activity分发的模块
        val map = ArrayMap<String, List<Int>>()
        map[PageConfig.MODULE_PAGE_NAME] = mutableListOf(R.id.distribute_relativelayout_pagename)
        map[PageConfig.MODULE_BODY_NAME] = mutableListOf(R.id.distribute_relativelayout_pagebodyA, R.id.distribute_relativelayout_pagebodyB)
        return map
    }
}
