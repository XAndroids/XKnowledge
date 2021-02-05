package com.android.xknowledge.sdk.ui.custom

import com.android.xknowledge.ListActivity

class CustomActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("CustomView", "自定义View", ViewActivity::class.java),
            ListItem("CustomViewGroup", "自定义ViewGroup", ViewGroupActivity::class.java)
        )
    }
}