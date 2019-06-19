package com.example.xknowledge.ui.view

import com.example.xknowledge.ListActivity
import com.example.xknowledge.ui.view.dialog.DialogActivity

class ViewActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RecyclerView", "RecyclerView相关", RecyclerViewActivity::class.java),
            ListItem("Dialog", "对话框相关", DialogActivity::class.java)
        )
    }
}