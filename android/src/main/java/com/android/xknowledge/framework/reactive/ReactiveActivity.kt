package com.android.xknowledge.framework.reactive

import com.android.xknowledge.ListActivity
import com.android.xknowledge.framework.reactive.rxbinding.RxBindingActivity

class ReactiveActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RxBinding", "Rxbinding库使用相关", RxBindingActivity::class.java)
        )
    }
}