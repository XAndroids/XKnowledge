package com.android.xknowledge.jetpack

import com.android.xknowledge.ListActivity
import com.android.xknowledge.jetpack.navigation.NavigationActivity
import com.android.xknowledge.jetpack.paging.PagingActivity

class JetpackActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Paging", "Paging库相关", PagingActivity::class.java),
            ListItem("Navigation", "Navigation库相关", NavigationActivity::class.java)
        )
    }
}
