package com.android.xknowledge.jetpack.paging.repository.inNet.byitem

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.android.xknowledge.jetpack.paging.api.RetrofitApi
import com.android.xknowledge.jetpack.paging.model.PagingData
import com.android.xknowledge.jetpack.paging.repository.NetworkState
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor

/**
 * 实现ItemKeyedDataSource子类从网络加载数据源
 * //FIXME ItemKeyedDataSource和ItemPaging具体的区别是？？
 */
class ItemKeyedPagingDataSource(
    private val mRetrofitApi: RetrofitApi,
    private val mSubredditName: String,
    private val mRetryExecutor: Executor
) : ItemKeyedDataSource<String, PagingData>() {

    //为retry event保留一个索引
    //() -> Any Function类型
    private var mRetry: (() -> Any)? = null

    val mNetworkState = MutableLiveData<NetworkState>()
    val mInitialLoad = MutableLiveData<NetworkState>()

    /**
     * 第一次请求的时候加载
     * loadInitial是由PagingAdapter和PagedList，DataSource自己内部驱动回调
     */
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<PagingData>
    ) {
        val request =
            mRetrofitApi.getTop(subreddit = mSubredditName, limit = params.requestedLoadSize)
        //更新网络状态
        //我们也给监听者提供一个初始化load状态，让UI可以知道什么时候第一页列表已经加载了。
        mNetworkState.postValue(NetworkState.LOADING)
        mInitialLoad.postValue(NetworkState.LOADING)

        try {
            val response = request.execute()
            val items = response.body()?.data?.children?.map { it.data } ?: emptyList()
            //网路请求成功，则更新相关网络状态，返回网路哦结果
            mRetry = null
            mNetworkState.postValue(NetworkState.LOADED)
            mInitialLoad.postValue(NetworkState.LOADED)
            callback.onResult(items)
        } catch (ioException: IOException) {
            //其实就是提供一个方法，但是这是使用了kotlin的方法类型和lambad表达式
            mRetry = {
                loadInitial(params, callback)
            }
            //网络请求失败，更新网路状态
            val error = NetworkState.error(ioException.message ?: "unknow error")
            mNetworkState.postValue(error)
            mInitialLoad.postValue(error)
        }
    }

    /**
     * 之后请求的加载
     */
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<PagingData>) {
        mNetworkState.postValue(NetworkState.LOADING)
        //after = params.key,应该和页面索引类似功能，表示当前请求到什么位置了
        mRetrofitApi.getTopAfter(
            subreddit = mSubredditName,
            after = params.key,
            limit = params.requestedLoadSize
        ).enqueue(
            object : retrofit2.Callback<RetrofitApi.ListingResponse> {
                override fun onFailure(call: Call<RetrofitApi.ListingResponse>, t: Throwable) {
                    mRetry = {
                        loadAfter(params, callback)
                    }
                    mNetworkState.postValue(NetworkState.error(t.message ?: "unknow err"))
                }

                override fun onResponse(
                    call: Call<RetrofitApi.ListingResponse>,
                    response: Response<RetrofitApi.ListingResponse>
                ) {
                    if (response.isSuccessful) {
                        val items = response.body()?.data?.children?.map { it.data } ?: emptyList()
                        mRetry = null
                        callback.onResult(items)
                        mNetworkState.postValue(NetworkState.LOADED)
                    } else {
                        mRetry = {
                            loadAfter(params, callback)
                        }
                        mNetworkState.postValue(NetworkState.error("error code:${response.code()}"))
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<PagingData>) {
        //不实现，因为我们只追加到初始加载
    }

    override fun getKey(item: PagingData): String = item.name

    fun retryAllFailed() {
        val preRetry = mRetry
        mRetry = null
        preRetry?.let {
            mRetryExecutor.execute {
                it.invoke()
            }
        }
    }
}