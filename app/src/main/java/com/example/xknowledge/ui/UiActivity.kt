package com.example.xknowledge.ui

import com.example.xknowledge.ListActivity

class UiActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("嵌套滑动机制", NestedScrollingActivity::class.java)
        )
    }
}
