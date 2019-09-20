package com.android.xknowledge.component.broadcast

import com.android.xknowledge.ListActivity

class BroadcastActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LocalBroadcast", "LocalBroadcast使用相关", LocalBroadcastActivity::class.java)
        )
    }
}