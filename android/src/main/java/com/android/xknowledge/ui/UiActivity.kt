package com.android.xknowledge.ui

import com.android.xknowledge.ListActivity
import com.android.xknowledge.ui.event.EventActivity
import com.android.xknowledge.ui.view.ViewActivity

class UiActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("View", "视图和组件使用相关", ViewActivity::class.java),
            ListItem("Event", "点击和滑动等交互相关", EventActivity::class.java)
        )
    }
}
