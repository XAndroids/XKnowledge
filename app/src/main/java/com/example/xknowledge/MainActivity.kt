package com.example.xknowledge

import com.example.xknowledge.ui.UiActivity

class MainActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("UI", "页面展示和交互相关", UiActivity::class.java)
        )
    }
}
