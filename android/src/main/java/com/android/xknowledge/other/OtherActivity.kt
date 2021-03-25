package com.android.xknowledge.other

import com.android.xknowledge.ListActivity

class OtherActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("AdbActivity", "执行Adb命令相关", AdbActivity::class.java),
            ListItem("HookActivity", "JAVA API Hook相关", HookActivity::class.java)
        )
    }
}