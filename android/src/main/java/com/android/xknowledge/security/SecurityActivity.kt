package com.android.xknowledge.security

import com.android.xknowledge.ListActivity
import com.android.xknowledge.security.flowdroid.FlowDroidJavaActivity

/**
 * 安全相关
 */
class SecurityActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem(
                "FlowDroidKotlinActivity",
                "FlowDroid静态检测Kotlin实现",
                FlowDroidJavaActivity::class.java
            ),
            ListItem("FlowDroidJavaActivity", "FlowDroid静态检测Java实现", FlowDroidJavaActivity::class.java)
        )
    }
}