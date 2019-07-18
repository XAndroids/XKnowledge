package com.example.xknowledge.component.activity

import com.example.xknowledge.ListActivity

class ActivityActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LifeCycle", "Activity的生命周期相关", LifeCycleActivity::class.java)
        )
    }
}