package com.example.xknowledge.ui.event

import com.example.xknowledge.ListActivity
import com.example.xknowledge.ui.event.nested.RecyclerViewActivity
import com.example.xknowledge.ui.event.nested.scrolling.NestedScrollingActivity
import com.example.xknowledge.ui.event.nested.ViewPagerActivity

class EventActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("NestedScrolling机制", "Android 5.0推出嵌套滑动机制", NestedScrollingActivity::class.java),
            ListItem("ViewPager嵌套", "", ViewPagerActivity::class.java),
            ListItem("RecyclerView双向嵌套", "竖向RecyclerView，Item中嵌套横向RecyclerView", RecyclerViewActivity::class.java)
        )
    }
}