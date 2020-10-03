package com.android.xknowledge.sdk.thread

import com.android.xknowledge.ListActivity

class ThreadActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Handler", "Handler相关", HandlerActivity::class.java),
            ListItem("AsyncTask", "AsyncTask相关", AsyncTaskActivity::class.java)
        )
    }
}