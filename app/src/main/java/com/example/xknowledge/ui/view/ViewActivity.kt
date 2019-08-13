package com.example.xknowledge.ui.view

import com.example.xknowledge.ListActivity
import com.example.xknowledge.ui.view.dialog.DialogActivity
import com.example.xknowledge.ui.view.recyclerview.RecyclerViewActivity
import com.example.xknowledge.ui.view.viewpager.ViewPagerCacheActivity

class ViewActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RecyclerView", "RecyclerView相关", RecyclerViewActivity::class.java),
            ListItem("Dialog", "对话框相关", DialogActivity::class.java),
            ListItem("ViewPager", "ViewPager缓存相关", ViewPagerCacheActivity::class.java)
        )
    }
}