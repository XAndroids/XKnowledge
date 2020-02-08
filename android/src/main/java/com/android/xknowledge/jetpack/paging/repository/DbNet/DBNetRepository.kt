package com.android.xknowledge.jetpack.paging.repository.DbNet

import com.android.xknowledge.jetpack.paging.model.PagingData
import com.android.xknowledge.jetpack.paging.repository.Listing
import com.android.xknowledge.jetpack.paging.repository.Repository

/**
 * 数据库+网络数据源
 */
class DBNetRepository : Repository {
    override fun postsOfSubreddit(subReddit: String, pageSize: Int): Listing<PagingData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}