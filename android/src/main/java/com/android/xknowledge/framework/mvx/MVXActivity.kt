package com.android.xknowledge.framework.mvx

import com.android.xknowledge.ListActivity
import com.android.xknowledge.framework.mvx.mvc.MVCActivity
import com.android.xknowledge.framework.mvx.mvn.MVNActivity
import com.android.xknowledge.framework.mvx.mvp.view.MVPActivity
import com.android.xknowledge.framework.mvx.mvvm.view.MVVMActivity

class MVXActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("MVN", "MVN-无模式", MVNActivity::class.java),
            ListItem("MVC", "MVC", MVCActivity::class.java),
            ListItem("MVP", "MVP", MVPActivity::class.java),
            ListItem("MVVM", "MVVM", MVVMActivity::class.java)
        )
    }
}