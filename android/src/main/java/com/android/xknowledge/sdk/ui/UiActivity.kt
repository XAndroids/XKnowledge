package com.android.xknowledge.sdk.ui

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.ui.dialog.DialogActivity
import com.android.xknowledge.sdk.ui.event.EventActivity
import com.android.xknowledge.sdk.ui.layout.LayoutActivity
import com.android.xknowledge.sdk.ui.link.LinkActivity
import com.android.xknowledge.sdk.ui.listener.NavigationBarActivity
import com.android.xknowledge.sdk.ui.statusbar.StatusBarActivity
import com.android.xknowledge.sdk.ui.view.ViewActivity

class UiActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("View", "视图和组件使用相关", ViewActivity::class.java),
            ListItem("Layout", "布局相关", LayoutActivity::class.java),
            ListItem("Dialog", "对话框相关", DialogActivity::class.java),
            ListItem("Event", "点击和滑动等交互相关", EventActivity::class.java),
            ListItem("Link", "跳转链接相关", LinkActivity::class.java),
            ListItem("StatusBar", "状态栏相关", StatusBarActivity::class.java),
            ListItem("NavigationBar", "导航栏相关", NavigationBarActivity::class.java)
        )
    }
}
