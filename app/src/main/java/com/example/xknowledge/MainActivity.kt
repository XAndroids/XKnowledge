package com.example.xknowledge

import com.example.xknowledge.ui.UiActivity as UiActivity1

class MainActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("界面", UiActivity1::class.java)
        )
    }
}
