package com.example.xknowledge.test

import com.example.xknowledge.ListActivity
import com.example.xknowledge.test.robolectric.RobolectricActivity

class TestActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RobolectricActivity", "Robolectric框架相关", RobolectricActivity::class.java)
        )
    }
}