package com.example.xknowledge.component.broadcast

import com.example.xknowledge.ListActivity

class BroadcastActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LocalBroadcast", "LocalBroadcast使用相关", LocalBroadcastActivity::class.java)
        )
    }
}