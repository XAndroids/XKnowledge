package com.android.xknowledge.jetpack.paging.repository

import com.android.xknowledge.jetpack.paging.model.PagingData

/**
 * 数据仓库接口
 */
interface Repository {
    /**
     * 数据仓库枚举类型
     */
    enum class Type {
        //从数据库获取
        DB,
        //从网络全量holder
        NET_BYITEM,
        //从网络分页
        NET_BYPAGE,
        //从网络和数据库
        DB_NET
    }

    /**
     * 使用输入的subReddit，发起网络请求，每页pageSize个
     */
    fun postsOfSubreddit(subReddit: String, pageSize: Int): Listing<PagingData>
}
