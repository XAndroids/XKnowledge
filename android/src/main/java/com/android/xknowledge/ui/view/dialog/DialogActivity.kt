package com.android.xknowledge.ui.view.dialog

import com.android.xknowledge.ListActivity

class DialogActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem(
                "WebViewDialog",
                "实现在全屏完全透明的DialogFragment中，嵌套全屏完全透明的WebView",
                DialogWebviewActivity::class.java
            ),
            ListItem(
                "DialogCheck",
                "实现当前页面是否存在Dialog展示",
                DialogCheckActivity::class.java
            )
        )
    }
}
