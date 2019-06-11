package com.example.xknowledge.ui.event

import com.example.xknowledge.ListActivity
import com.example.xknowledge.ui.event.nested.ScrollingActivity
import com.example.xknowledge.ui.event.nested.ViewPagerActivity

class EventActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("嵌套滑动机制", ScrollingActivity::class.java),
            ListItem("ViewPager嵌套", ViewPagerActivity::class.java)
        )
    }
}