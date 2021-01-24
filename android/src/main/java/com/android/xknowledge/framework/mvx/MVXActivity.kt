package com.android.xknowledge.framework.mvx

import com.android.xknowledge.ListActivity
import com.android.xknowledge.framework.mvx.mvc.MVCActivity
import com.android.xknowledge.framework.mvx.mvn.MVNActivity

class MVXActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("MVN", "MVN-无模式", MVNActivity::class.java),
            ListItem("MVC", "MVC", MVCActivity::class.java)
        )
    }
}