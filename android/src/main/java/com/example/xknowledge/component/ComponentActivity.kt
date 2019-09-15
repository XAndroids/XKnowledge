package com.example.xknowledge.component

import com.example.xknowledge.ListActivity
import com.example.xknowledge.component.activity.ActivityActivity
import com.example.xknowledge.component.broadcast.BroadcastActivity

class ComponentActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Activity", "Activity相关", ActivityActivity::class.java),
            ListItem("Broadcast", "Broadcast相关", BroadcastActivity::class.java)
        )
    }
}