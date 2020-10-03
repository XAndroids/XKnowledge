package com.android.xknowledge.open

import com.android.xknowledge.ListActivity
import com.android.xknowledge.open.image.fresco.FrescoActivity
import com.android.xknowledge.open.message.eventbus.EventbusActivity
import com.android.xknowledge.open.reactive.ReactiveActivity

class OpenActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("EventBus", "EventBus使用", EventbusActivity::class.java),
            ListItem("Fresco", "Fresco使用", FrescoActivity::class.java),
            ListItem("Reactive", "响应式编程", ReactiveActivity::class.java)
        )
    }
}