package com.android.xknowledge.jetpack.paging

import com.android.xknowledge.ListActivity
import com.android.xknowledge.jetpack.paging.ui.DbActivity
import com.android.xknowledge.jetpack.paging.ui.DbAndNetActivity
import com.android.xknowledge.jetpack.paging.ui.NetByItemActivity
import com.android.xknowledge.jetpack.paging.ui.NetByPagingActivity

class PagingActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Db", "Paging Db数据源", DbActivity::class.java),
            ListItem("Net Item", "Paging Net+Item数据源", NetByItemActivity::class.java),
            ListItem("Net Page", "Paging Net+Paging数据源", NetByPagingActivity::class.java),
            ListItem("Local + Net", "Local和Net数据源", DbAndNetActivity::class.java)
        )
    }
}
