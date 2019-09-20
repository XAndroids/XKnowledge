package com.android.xknowledge.framework

import com.android.xknowledge.ListActivity
import com.android.xknowledge.framework.eventbus.EventbusActivity

class FrameworkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("EventBus", "EventBus使用", EventbusActivity::class.java)
        )
    }

}
