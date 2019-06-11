package com.example.xknowledge

import com.example.xknowledge.ui.UiActivity

class MainActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("UI", UiActivity::class.java)
        )
    }
}
