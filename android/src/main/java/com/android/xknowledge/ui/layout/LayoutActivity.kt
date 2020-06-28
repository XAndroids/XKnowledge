package com.android.xknowledge.ui.layout

import com.android.xknowledge.ListActivity

class LayoutActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("include/merge", "include/merge标签相关", IncludeActivity::class.java)
        )
    }
}
