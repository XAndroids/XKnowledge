package com.example.xknowledge.ui

import com.example.xknowledge.ListActivity
import com.example.xknowledge.ui.event.EventActivity
import com.example.xknowledge.ui.view.ViewActivity

class UiActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("View", ViewActivity::class.java),
            ListItem("Event", EventActivity::class.java)
        )
    }
}
