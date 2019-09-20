package com.android.xknowledge.component.activity

import com.android.xknowledge.ListActivity

class ActivityActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LifeCycle", "Activity的生命周期相关", LifeCycleActivity::class.java)
        )
    }
}