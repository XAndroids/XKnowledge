package com.android.xknowledge.thread

import com.android.xknowledge.ListActivity

class ThreadActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("HandlerActivity", "Handler相关", HandlerActivity::class.java)
        )
    }
}