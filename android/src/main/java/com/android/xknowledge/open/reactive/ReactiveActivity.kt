package com.android.xknowledge.open.reactive

import com.android.xknowledge.ListActivity
import com.android.xknowledge.open.reactive.rxbinding.RxBindingActivity

class ReactiveActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("RxBinding", "Rxbinding库使用相关", RxBindingActivity::class.java)
        )
    }
}