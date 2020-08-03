package com.android.xknowledge.ui.event

import com.android.xknowledge.ListActivity
import com.android.xknowledge.ui.event.distribute.EventDistributeActivity
import com.android.xknowledge.ui.event.nested.RecyclerViewActivity
import com.android.xknowledge.ui.event.nested.scrolling.NestedScrollingActivity
import com.android.xknowledge.ui.event.nested.ViewPagerActivity
import com.android.xknowledge.ui.event.ontouch.ViewEventActivity

class EventActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem(
                "事件分发机制",
                "查看Android事件从Activity，ViewGroup和View的分发过程",
                EventDistributeActivity::class.java
            ),
            ListItem(
                "NestedScrolling机制",
                "Android 5.0推出嵌套滑动机制",
                NestedScrollingActivity::class.java
            ),
            ListItem(
                "RecyclerView双向嵌套",
                "竖向RecyclerView，Item中嵌套横向RecyclerView",
                RecyclerViewActivity::class.java
            ),
            ListItem("ViewPager嵌套", "", ViewPagerActivity::class.java),
            ListItem("onTouch/Click", "", ViewEventActivity::class.java)
        )
    }
}