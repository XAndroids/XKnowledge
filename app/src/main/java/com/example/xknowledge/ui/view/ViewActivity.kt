package com.example.xknowledge.ui.view

import com.example.xknowledge.ListActivity

class ViewActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RecyclerView", RecyclerViewActivity::class.java)
        )
    }
}