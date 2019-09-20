package com.android.xknowledge.hybrid

import com.android.xknowledge.ListActivity
import com.android.xknowledge.hybrid.jsinvoke.JSInvokeActivity
import com.android.xknowledge.hybrid.webview.WebviewActivity

class HybridActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Webview", "Webview的基本使用", WebviewActivity::class.java),
            ListItem("JSInvoke", "Webview和JavaScript相互调用", JSInvokeActivity::class.java)
        )
    }
}
