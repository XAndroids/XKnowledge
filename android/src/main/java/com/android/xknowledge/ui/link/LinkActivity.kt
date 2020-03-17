package com.android.xknowledge.ui.link

import com.android.xknowledge.ListActivity

class LinkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem(
                "DeepLink",
                "创建DeepLink跳转页面",
                DeepLinkActivity::class.java
            ),
            ListItem(
                "AppLink",
                "创建AppLink跳转页面",
                AppLinkActivity::class.java
            )
        )
    }
}