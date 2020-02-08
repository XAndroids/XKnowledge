package com.android.xknowledge.jetpack.paging.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R
import com.android.xknowledge.jetpack.paging.repository.NetworkState
import com.android.xknowledge.jetpack.paging.repository.Status

/**
 * 网络状态Item ViewHolder，显示网路请求Loading和网络错误Retry状态
 */
class NetItemViewHolder(view: View, private val mRetryCallback: () -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val progressBar = view.findViewById<ProgressBar>(R.id.pagingitem_progressbar)
    private val retry = view.findViewById<Button>(R.id.pagingitem_retry_button)
    private val errorMsg = view.findViewById<TextView>(R.id.pagingitem_errormsg_textview)

    init {
        retry.setOnClickListener {
            mRetryCallback()
        }
    }

    fun bind(networkState: NetworkState?) {
        progressBar.visibility = toVisibility(networkState?.status == Status.RUNNING)
        retry.visibility = toVisibility(networkState?.status == Status.FAILED)
        errorMsg.visibility = toVisibility(networkState?.message != null)
        errorMsg.text = networkState?.message
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_paging_netitem, parent, false)
            return NetItemViewHolder(view, retryCallback)
        }

        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}
