package com.android.xknowledge.jetpack.paging.ui

import com.android.xknowledge.jetpack.paging.repository.Repository

class NetByItemActivity : PagingListActivity() {
    override fun getRepositoryType(): Repository.Type {
        return Repository.Type.NET_BYITEM
    }

}
