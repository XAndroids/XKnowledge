package com.android.xknowledge.sdk.ui.statusbar

import com.android.xknowledge.ListActivity

class StatusBarActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(ListItem("ImmersiveActivity", "沉浸式页面", ImmersiveActivity::class.java))
    }
}