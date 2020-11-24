package com.android.xknowledge.sdk

import com.android.xknowledge.ListActivity
import com.android.xknowledge.sdk.component.ComponentActivity
import com.android.xknowledge.sdk.other.MockExitActivity
import com.android.xknowledge.sdk.other.UploadExitActivity
import com.android.xknowledge.sdk.storage.StorageActivity
import com.android.xknowledge.sdk.thread.ThreadActivity
import com.android.xknowledge.sdk.ui.UiActivity

class SdkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("UI", "页面展示和交互相关", UiActivity::class.java),
            ListItem("Compontent", "四大组件相关", ComponentActivity::class.java),
            ListItem("Storage", "存储相关", StorageActivity::class.java),
            ListItem("Thread", "线程相关", ThreadActivity::class.java),
            ListItem("mockexit", "模拟退出", MockExitActivity::class.java),
            ListItem("newfuture", "新功能", UploadExitActivity::class.java)

        )
    }
}
