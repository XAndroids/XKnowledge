package com.android.xknowledge.ui.view.dialog

import com.android.xknowledge.ListActivity

class DialogActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem(
                "WebViewDialog",
                "实现在全屏透明的DialogFragment中，嵌套全屏透明的WebView",
                DialogWebviewActivity::class.java
            ),
            ListItem(
                "DialogCheck",
                "实现当前页面是否存在Dialog展示",
                DialogCheckActivity::class.java
            ), ListItem(
                "DialogTranslucent",
                "实现Dialog有半透明蒙层和无蒙层样式",
                DialogTranslucentActivity::class.java
            )
        )
    }
}
