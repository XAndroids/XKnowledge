package com.android.xknowledge.component.activity.distribute

import android.os.Bundle
import androidx.collection.ArrayMap
import com.android.xknowledge.R

/**
 * 组件分发Activity
 */
class DistributeMainActivity : DistributeManagerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distributemain)
    }

    override fun moduleConfig(): ArrayMap<String, List<Int>> {
        val map = ArrayMap<String, List<Int>>()
        map[PageConfig.MODULE_PAGE_NAME] = mutableListOf(R.id.page_name)
        map[PageConfig.MODULE_BODY_NAME] = mutableListOf(R.id.page_bodyT, R.id.page_bodyB)
        return map
    }
}
