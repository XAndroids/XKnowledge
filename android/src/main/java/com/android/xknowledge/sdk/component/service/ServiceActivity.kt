package com.android.xknowledge.sdk.component.service

import com.android.xknowledge.ListActivity

class ServiceActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("IntentService", "IntentService", IntentServiceActivity::class.java)
        )
    }
}