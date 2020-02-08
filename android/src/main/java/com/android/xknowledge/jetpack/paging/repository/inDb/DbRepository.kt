package com.android.xknowledge.jetpack.paging.repository.inDb

import com.android.xknowledge.jetpack.paging.model.PagingData
import com.android.xknowledge.jetpack.paging.repository.Listing
import com.android.xknowledge.jetpack.paging.repository.Repository

/**
 * 数据库数据源
 */
class DbRepository : Repository {
    override fun postsOfSubreddit(subReddit: String, pageSize: Int): Listing<PagingData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}