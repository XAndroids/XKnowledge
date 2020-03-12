package com.android.xknowledge.framework.reactive.rxbinding

import com.android.xknowledge.ListActivity

class RxBindingActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RxClick", "Rxbinding点击事件相关", ClickActivity::class.java),
            ListItem("RxForm", "Rxbinding表单功能实现", FormActivity::class.java),
            ListItem("RxVerify", "Rxbinding验证码功能实现", VerifyActivity::class.java),
            ListItem("RxRecycler", "Rxbinding列表事件相关", RecyclerActivity::class.java),
            ListItem("RxPermission", "Rxbinding权限申请相关", PermissionActivity::class.java)
        )
    }
}