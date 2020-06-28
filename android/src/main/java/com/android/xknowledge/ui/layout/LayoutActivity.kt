package com.android.xknowledge.ui.layout

import com.android.xknowledge.ListActivity

class LayoutActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("include", "include标签相关", IncludeActivity::class.java),
            ListItem("merge", "merge标签相关", MergeActivity::class.java)
        )
    }
}
