package com.android.xknowledge.jetpack.paging.ui

import com.android.xknowledge.jetpack.paging.repository.Repository

class DbAndNetActivity : PagingListActivity() {
    override fun getRepositoryType(): Repository.Type {
        return Repository.Type.DB_NET
    }

}
