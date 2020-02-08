package com.android.xknowledge.jetpack.paging.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.xknowledge.jetpack.paging.repository.Repository

/**
 * Paging列表页面的ViewModel
 */
class PagingListViewModel(private val mRepository: Repository) : ViewModel() {
    //搜索输入的名称，输入框监听
    private val intputName = MutableLiveData<String>()

    //监听subredditName的变化，发起网络请求列表
    //Transformations.map，监听一个LiveData，转换成一个新的LiveData
    private val mRepoResult = Transformations.map(intputName) {
        mRepository.postsOfSubreddit(it, 30)
    }

    //将数据源返回的mPagedList中的mPagedList转换到ViewModel中，供UI观察更新
    //Transformations.switchMap，同mao()，返回必须是LiveData，并且只观察最新的数据
    val mPagingList = Transformations.switchMap(mRepoResult) { it.mPagedList }!!
    val mNetWorkState = Transformations.switchMap(mRepoResult) { it.mNetworkState }!!
    val mRefreshState = Transformations.switchMap(mRepoResult) { it.mRefreshState }!!


    /**
     * 展示和更新最新输入的名称
     */
    fun showSubreddit(subreddit: String): Boolean {
        if (intputName.value == subreddit) {
            return false
        }
        //触发观察inputName的观察者发起网络请求
        intputName.value = subreddit
        return true
    }

    fun retry() {
        val listing = mRepoResult?.value
        listing?.mRetry?.invoke()
    }
}

