package com.example.xknowledge.ui

import com.example.xknowledge.ListActivity
import com.example.xknowledge.ui.nested.scrolling.NestedScrollingActivity
import com.example.xknowledge.ui.nested.viewpager.NestedViewPagerActivity

class UiActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("嵌套滑动机制", NestedScrollingActivity::class.java),
            ListItem("ViewPager嵌套", NestedViewPagerActivity::class.java)
        )
    }
}
