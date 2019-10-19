package com.android.xknowledge.component.activity

import com.android.xknowledge.ListActivity
import com.android.xknowledge.component.activity.distribute.DistributeActivity
import com.android.xknowledge.component.activity.lifecycler.LifeCycleActivity

class ActivityActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LifeCycle", "Activity的生命周期相关", LifeCycleActivity::class.java),
            ListItem("Distribute", "跨组件Activity分发相关", DistributeActivity::class.java)
        )
    }
}