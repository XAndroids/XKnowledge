package com.example.xknowledge.component

import com.example.xknowledge.ListActivity
import com.example.xknowledge.component.activity.ActivityActivity

class ComponentActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Activity", "Activity相关", ActivityActivity::class.java)
        )
    }
}