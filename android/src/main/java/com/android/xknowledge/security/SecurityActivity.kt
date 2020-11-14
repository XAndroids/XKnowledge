package com.android.xknowledge.security

import com.android.xknowledge.ListActivity
import com.android.xknowledge.security.danger.DangerActivity
import com.android.xknowledge.security.flowdroid.FlowDroidJavaActivity
import com.android.xknowledge.security.permission.CustomPermissionActivity

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
            ListItem(
                "FlowDroidJavaActivity",
                "FlowDroid静态检测Java实现",
                FlowDroidJavaActivity::class.java
            ),
            ListItem(
                "CustomPermissionActivity",
                "自定义Permission",
                CustomPermissionActivity::class.java
            ), ListItem(
                "DangerActivity",
                "危险权限调用",
                DangerActivity::class.java
            )
        )
    }
}