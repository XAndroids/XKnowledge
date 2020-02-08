package com.android.xknowledge.jetpack.paging.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * 网络请求返回结果数据类
 */
data class Listing<T>(
    //数据类的数据，只会随着数据类数据更新，重新创建，不会修改值所以使用LiveData

    //列表展示数据
    val mPagedList: LiveData<PagedList<T>>,
    //列表网络展示的不同状态：网络请求成功有数据，正在网络请求Loading，网路请求失败提示
    val mNetworkState: LiveData<NetworkState>,
    //下拉列表的Loading状态：网络请求成功或失败，不展示；网络请求中Loading
    val mRefreshState: LiveData<NetworkState>,
    //使用Kotlin的函数类型，代替callback回调
    //刷新整个数据并重新获取
    val mRefresh: () -> Unit,
    //重试任何字段请求
    val mRetry: () -> Unit
)
