package com.android.xknowledge.sdk.ui.custom

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.ui.custom.view.ViewActivity
import com.android.xknowledge.sdk.ui.custom.viewgroup.ViewGroupActivity
import com.android.xknowledge.sdk.ui.custom.viewpager.ViewPagerItemActivity

class CustomActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("CustomView", "自定义View", ViewActivity::class.java),
            ListItem("CustomViewGroup", "自定义ViewGroup", ViewGroupActivity::class.java),
            ListItem("ViewPagerItem", "ViewPagerItem高度", ViewPagerItemActivity::class.java)
        )
    }
}