package com.android.xknowledge.test

import com.android.xknowledge.ListActivity
import com.android.xknowledge.test.robolectric.RobolectricActivity

class TestActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RobolectricActivity", "Robolectric框架相关", RobolectricActivity::class.java)
        )
    }
}