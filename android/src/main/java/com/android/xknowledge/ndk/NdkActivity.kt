package com.android.xknowledge.ndk

import com.android.xknowledge.ListActivity

class NdkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Base", "ndk基本环境和使用", BaseNdkActivity::class.java)
        )
    }
}