package com.android.xknowledge.jetpack.paging.ui

import com.android.xknowledge.jetpack.paging.repository.Repository

class DbActivity : PagingListActivity() {
    override fun getRepositoryType(): Repository.Type {
        return Repository.Type.DB
    }
}
