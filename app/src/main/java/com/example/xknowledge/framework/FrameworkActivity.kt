package com.example.xknowledge.framework

import com.example.xknowledge.ListActivity
import com.example.xknowledge.framework.eventbus.EventbusActivity

class FrameworkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("EventBus", "EventBus使用", EventbusActivity::class.java)
        )
    }

}
