package com.android.xknowledge.jetpack.paging.repository.inNet.byitem

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.xknowledge.jetpack.paging.api.RetrofitApi
import com.android.xknowledge.jetpack.paging.model.PagingData
import java.util.concurrent.Executor

/**
 * 创建DataSource.Factory的自来，将自定义数据加载到PageList对象中
 */
class NetItemDataSourceFactory(
    private val mRetrofitApi: RetrofitApi,
    private val mSubredditName: String,
    private val mRetryExecutor: Executor
) : DataSource.Factory<String, PagingData>() {

    val mSourceLiveData = MutableLiveData<ItemKeyedPagingDataSource>()

    override fun create(): DataSource<String, PagingData> {
        val source = ItemKeyedPagingDataSource(mRetrofitApi, mSubredditName, mRetryExecutor)
        mSourceLiveData.postValue(source)
        return source
    }
}