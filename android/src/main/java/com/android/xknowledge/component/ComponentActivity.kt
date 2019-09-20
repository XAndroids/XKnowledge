package com.android.xknowledge.component

import com.android.xknowledge.ListActivity
import com.android.xknowledge.component.activity.ActivityActivity
import com.android.xknowledge.component.broadcast.BroadcastActivity

class ComponentActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Activity", "Activity相关", ActivityActivity::class.java),
            ListItem("Broadcast", "Broadcast相关", BroadcastActivity::class.java)
        )
    }
}