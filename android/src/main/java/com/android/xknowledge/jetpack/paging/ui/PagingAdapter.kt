package com.android.xknowledge.jetpack.paging.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R
import com.android.xknowledge.jetpack.paging.model.PagingData
import com.android.xknowledge.jetpack.paging.repository.NetworkState
import com.android.xknowledge.jetpack.paging.utils.GlideRequests

/**
 * PagedListAdapter，将PagedList数据在列表中渲染
 */
class PagingAdapter(
    private val mGlide: GlideRequests,
    //该写法避免了Callcak接口的定义，直接使用方法类型
    //参考：函数类型，https://www.kotlincn.net/docs/reference/lambdas.html#%E5%87%BD%E6%95%B0%E7%B1%BB%E5%9E%8B
    private val mRetryCallback: () -> Unit
) :
    PagedListAdapter<PagingData, RecyclerView.ViewHolder>(POST_COMPARETOR) {

    //当前的网络状态，用于传入ViewHolder用于更新NetItem展示
    private var mNetworkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //创建不同item的Holder数据
        return when (viewType) {
            R.layout.recycler_paging_item -> PagingItemViewHolder.create(parent, mGlide)
            R.layout.recycler_paging_netitem -> NetItemViewHolder.create(
                parent,
                mRetryCallback
            )
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //根据Item类型进行数据绑定
        when (getItemViewType(position)) {
            R.layout.recycler_paging_item -> (holder as PagingItemViewHolder).bind(getItem(position))
            R.layout.recycler_paging_netitem -> (holder as NetItemViewHolder).bind(
                mNetworkState
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        //payloads用于局部更新UI
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
            (holder as PagingItemViewHolder).updateScore(item)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        //如果有额外行，且为最后一行，则显示net布局
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.recycler_paging_netitem
        } else {
            R.layout.recycler_paging_item
        }
    }

    override fun getItemCount(): Int {
        //如果有扩展行，展示网络状态则增加item行数+1
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    /**
     * 设置网络状态
     */
    fun setNetworkState(networkState: NetworkState?) {
        val previousState = this.mNetworkState
        val hadExtraRow = hasExtraRow()
        this.mNetworkState = networkState
        val hasExtraRow = hasExtraRow()
        //如果前后网络状态改变了
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                //且原来有额外行，现在没有，则移除最后的额外航
                notifyItemRemoved(super.getItemCount())
            } else {
                //如果原来没有额外行，现在有，则插入最后的额外行
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != networkState) {
            //如果原来和现在都还有额外行，且前后网络状态不一致，在网络失败和Loading中切换
            //则通知一行额外航，更新展示即可
            notifyItemChanged(itemCount - 1)
        }
    }

    /**
     * 如果NetworkState为Loading和Faild状态，就需要额外的Row来展示Loading和Retry布局Item
     */
    private fun hasExtraRow() = mNetworkState != null && mNetworkState != NetworkState.LOADED

    companion object {
        private val PAYLOAD_SCORE = Any()

        val POST_COMPARETOR = object : DiffUtil.ItemCallback<PagingData>() {
            override fun areItemsTheSame(oldItem: PagingData, newItem: PagingData): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: PagingData, newItem: PagingData): Boolean =
                oldItem.name == newItem.name

            override fun getChangePayload(oldItem: PagingData, newItem: PagingData): Any? {
                return if (sameExceptScore(oldItem, newItem)) {
                    PAYLOAD_SCORE
                } else {
                    null
                }
            }

            private fun sameExceptScore(oldItem: PagingData, newItem: PagingData): Boolean {
                // DON'T do this copy in a real app, it is just convenient here for the demo :)
                // because reddit randomizes scores, we want to pass it as a payload to minimize
                // UI updates between refreshes
                return oldItem.copy(score = newItem.score) == newItem
            }
        }
    }
}