package com.android.xknowledge.ui.view

import com.android.xknowledge.ListActivity
import com.android.xknowledge.ui.view.covered.CoveredActivity
import com.android.xknowledge.ui.view.dialog.DialogActivity
import com.android.xknowledge.ui.view.recyclerview.RecyclerViewActivity
import com.android.xknowledge.ui.view.viewpager.ViewPagerCacheActivity
import com.android.xknowledge.ui.view.viewstub.ViewStubActivity

class ViewActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RecyclerView", "RecyclerView相关", RecyclerViewActivity::class.java),
            ListItem("Dialog", "对话框相关", DialogActivity::class.java),
            ListItem("ViewPager", "ViewPager缓存相关", ViewPagerCacheActivity::class.java),
            ListItem("Covered", "View遮挡检测相关", CoveredActivity::class.java),
            ListItem("ViewStub", "ViewStub使用相关", ViewStubActivity::class.java)
        )
    }
}