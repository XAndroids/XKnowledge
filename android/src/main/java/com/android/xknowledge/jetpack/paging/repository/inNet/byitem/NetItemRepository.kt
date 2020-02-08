package com.android.xknowledge.jetpack.paging.repository.inNet.byitem

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.android.xknowledge.jetpack.paging.api.RetrofitApi
import com.android.xknowledge.jetpack.paging.model.PagingData
import com.android.xknowledge.jetpack.paging.repository.Listing
import com.android.xknowledge.jetpack.paging.repository.Repository
import java.util.concurrent.Executor

/**
 * 网络数据源
 */
class NetItemRepository(
    private val mRetrofitApi: RetrofitApi,
    private val mNetExecutor: Executor
) : Repository {

    override fun postsOfSubreddit(subReddit: String, pageSize: Int): Listing<PagingData> {
        val sourceFactory = NetItemDataSourceFactory(mRetrofitApi, subReddit, mNetExecutor)

        //我们在这里使用toLiveData Kotlin ext功能，你也可以使用LivePagedListBuilder。
        //其实toLiveData就是调用了LivePagedListBuilder，ktx的思路就是封装一些模板代码。
        val livePagedList = sourceFactory.toLiveData(
            //我们在这里使用Config Kotlin ext，也可为使用PagedList.Config.Builder
            //enablePlaceholders：直接展示列表完整长度，第二，n页数据还没回来之前，展示placeholder
            //参考：https://enginebai.com/2019/04/22/android-paging-part1/
            config = Config(
                pageSize = pageSize, enablePlaceholders = false, initialLoadSizeHint = pageSize * 2
            ),
            //提供自定义网络请求执行器，否则它将默认为Arch组件的IO池，该IOc池也用于磁盘请求。
            fetchExecutor = mNetExecutor
        )

        //将PagedList和DataSource中相关的状态信息，封装传递给ViewModel
        val refreshState = Transformations.switchMap(sourceFactory.mSourceLiveData) {
            it.mInitialLoad
        }
        return Listing(
            mPagedList = livePagedList,
            mNetworkState = Transformations.switchMap(sourceFactory.mSourceLiveData) {
                it.mNetworkState
            }, mRetry = {
                sourceFactory.mSourceLiveData.value?.retryAllFailed()
            }, mRefresh = {
                sourceFactory.mSourceLiveData.value?.invalidate()
            }, mRefreshState = refreshState
        )
    }
}