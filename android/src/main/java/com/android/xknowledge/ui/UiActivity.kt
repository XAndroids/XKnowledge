package com.android.xknowledge.ui

import com.android.xknowledge.ListActivity
import com.android.xknowledge.ui.dialog.DialogActivity
import com.android.xknowledge.ui.event.EventActivity
import com.android.xknowledge.ui.link.LinkActivity
import com.android.xknowledge.ui.statusbar.StatusBarActivity
import com.android.xknowledge.ui.view.ViewActivity

class UiActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("View", "视图和组件使用相关", ViewActivity::class.java),
            ListItem("Dialog", "对话框相关", DialogActivity::class.java),
            ListItem("Event", "点击和滑动等交互相关", EventActivity::class.java),
            ListItem("Link", "跳转链接相关", LinkActivity::class.java),
            ListItem("StatusBar", "状态栏相关", StatusBarActivity::class.java)
        )
    }
}
