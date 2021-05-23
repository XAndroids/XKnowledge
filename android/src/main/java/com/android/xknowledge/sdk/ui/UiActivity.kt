package com.android.xknowledge.sdk.ui

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.ui.animation.ScalAnimationActivity
import com.android.xknowledge.sdk.ui.animator.ObjectAnimatorActivity
import com.android.xknowledge.sdk.ui.custom.CustomActivity
import com.android.xknowledge.sdk.ui.dialog.DialogActivity
import com.android.xknowledge.sdk.ui.event.EventActivity
import com.android.xknowledge.sdk.ui.height.HeightActivity
import com.android.xknowledge.sdk.ui.layout.LayoutActivity
import com.android.xknowledge.sdk.ui.link.LinkActivity
import com.android.xknowledge.sdk.ui.listener.NavigationBarActivity
import com.android.xknowledge.sdk.ui.notification.NotificationActivity
import com.android.xknowledge.sdk.ui.setting.SettingActivity
import com.android.xknowledge.sdk.ui.statusbar.StatusBarActivity
import com.android.xknowledge.sdk.ui.theme.ThemeActivity
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
            ListItem("NavigationBar", "导航栏相关", NavigationBarActivity::class.java),
            ListItem("Notification", "通知相关", NotificationActivity::class.java),
            ListItem("Theme", "主题相关", ThemeActivity::class.java),
            ListItem("Setting", "设置相关", SettingActivity::class.java),
            ListItem("Height", "屏幕高度", HeightActivity::class.java),
            ListItem("CustomView", "自定义View相关", CustomActivity::class.java),
            ListItem("ObjectAnimator", "属性动画", ObjectAnimatorActivity::class.java),
            ListItem("ScalAnimation", "补间动画", ScalAnimationActivity::class.java)
        )
    }
}
