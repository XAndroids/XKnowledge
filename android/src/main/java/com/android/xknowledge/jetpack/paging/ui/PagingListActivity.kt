package com.android.xknowledge.jetpack.paging.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.android.xknowledge.jetpack.paging.model.PagingData
import com.android.xknowledge.jetpack.paging.repository.NetworkState
import com.android.xknowledge.jetpack.paging.repository.Repository
import com.android.xknowledge.jetpack.paging.utils.GlideApp
import com.android.xknowledge.jetpack.paging.utils.RepositoryLocator
import kotlinx.android.synthetic.main.activity_pageing_list.*

/**
 * Paging组件类表基础类
 */
abstract class PagingListActivity : TitleActivity() {

    abstract fun getRepositoryType(): Repository.Type

    //by viewModels，Ktx，用于创建viewModel
    //参考：Fragment Ktx，https://developer.android.com/kotlin/ktx
    private val mPagingListViewModel: PagingListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val redditPostRepository = RepositoryLocator.instance(this@PagingListActivity)
                    .getRepository(getRepositoryType())
                @Suppress("UNCHECKED_CAST")
                return PagingListViewModel(redditPostRepository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pageing_list)
        initAdapter()
        initSwipeToRefresh()
        initSearch()
        val subreddit = savedInstanceState?.getString(KEY_SUBREDDIT) ?: DEFAULT_SUBREDDIT
        mPagingListViewModel.showSubreddit(subreddit)
    }

    private fun initSearch() {
        //如果函数最后一个参数是函数，作为相应的参数传入的lambda表达式可以放在圆括号外面
        //参考：传递末尾的 lambda 表达式，https://www.kotlincn.net/docs/reference/lambdas.html
        pagelist_edittext.setOnEditorActionListener { _, actionId, _ ->
            //在Kotlin中，if是一个表达式，它会返回一个值。因此就不需要三目运算符了
            //参考：If表达式，https://www.kotlincn.net/docs/reference/control-flow.html
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updatedSubredditFromInput()
                true
            } else {
                false
            }
        }

        pagelist_edittext.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updatedSubredditFromInput()
                true
            } else {
                false
            }
        }
    }

    private fun initSwipeToRefresh() {
        //观察model中列表下拉Loading状态
        mPagingListViewModel.mRefreshState.observe(this, Observer {
            pagelist_swiperefresh.isRefreshing = it == NetworkState.LOADING
        })
    }

    private fun initAdapter() {
        val glide = GlideApp.with(this);
        val postsAdapter = PagingAdapter(glide) {
            mPagingListViewModel.retry()
        }
        //Kotlin Android Extensions：使用View Binding，避免findViewById()
        //参考：https://kotlinlang.org/docs/tutorials/android-plugin.html
        pagelist_list.adapter = postsAdapter
        //观察model中的mRedditPost数据，即使更新列表，并恢复列表的状态
        mPagingListViewModel.mPagingList.observe(this, Observer<PagedList<PagingData>> {
            postsAdapter.submitList(it) {
                //刷新列表之后，重新恢复列表滑动的位置
                val layoutManager = pagelist_list.layoutManager as LinearLayoutManager
                val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (position != RecyclerView.NO_POSITION) {
                    pagelist_list.scrollToPosition(position)
                }
            }
        })
        //观察model中的mNetWorkState数据，更新网络状态
        mPagingListViewModel.mNetWorkState.observe(this, Observer {
            postsAdapter.setNetworkState(it)
        })
    }

    private fun updatedSubredditFromInput() {
        pagelist_edittext.text.trim().toString().let {
            if (it.isNotEmpty()) {
                if (mPagingListViewModel.showSubreddit(it)) {
                    pagelist_list.scrollToPosition(0)
                    (pagelist_list.adapter as PagingAdapter).submitList(null)
                }
            }
        }
    }

    companion object {
        const val KEY_SUBREDDIT = "subreddit"
        const val DEFAULT_SUBREDDIT = "androiddev"
    }
}
