package com.android.xknowledge.sdk.ui.event

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.ui.event.conflict.viewpager.ViewPagerConflictActivity
import com.android.xknowledge.sdk.ui.event.distribute.EventDistributeActivity
import com.android.xknowledge.sdk.ui.event.nested.RecyclerViewActivity
import com.android.xknowledge.sdk.ui.event.nested.scrolling.NestedScrollingActivity
import com.android.xknowledge.sdk.ui.event.nested.ViewPagerActivity
import com.android.xknowledge.sdk.ui.event.ontouch.ViewEventActivity

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
            ListItem("ViewPager嵌套", "ViewPager嵌套相关", ViewPagerActivity::class.java),
            ListItem("ViewEvent", "View的事件分发流程", ViewEventActivity::class.java),
            ListItem("ViewPager事件冲突", "ViewPager事件冲突", ViewPagerConflictActivity::class.java)
        )
    }
}