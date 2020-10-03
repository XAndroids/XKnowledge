package com.android.xknowledge.sdk.component.activity.distribute.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.collection.ArrayMap
import com.android.xknowledge.R
import com.android.xknowledge.sdk.component.activity.distribute.activity.PageConfig
import java.util.ArrayList

class DistributeView(context: Context?, savedInstanceState: Bundle?, rootView: View) :
    ModuleManagerView(context, savedInstanceState, rootView) {
    
    override fun moduleConfig(): ArrayMap<String, List<Int>> {
        val map = ArrayMap<String, List<Int>>()
        map[PageConfig.MODULE_VIEW_PAGE_NAME] = object : ArrayList<Int>() {init { add(R.id.distributeview_relativelayout_pageviewname) } }
        return map
    }
}