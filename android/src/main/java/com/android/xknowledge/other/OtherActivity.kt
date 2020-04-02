package com.android.xknowledge.other

import com.android.xknowledge.ListActivity
import com.android.xknowledge.TitleActivity

class OtherActivity : ListActivity() {
    override fun getMyListItemList(): List<ListActivity.ListItem> {
        return listOf(
            ListActivity.ListItem("AdbActivity", "执行Adb命令相关", AdbActivity::class.java)
        )
    }
}