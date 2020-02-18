package com.android.xknowledge.jetpack.paging.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R
import com.android.xknowledge.jetpack.paging.model.PagingData
import com.android.xknowledge.jetpack.paging.utils.GlideRequests

/**
 * Paging列表item ViewHolder
 */
class PagingItemViewHolder(
    view: View,
    private val mGlide: GlideRequests
) :
    RecyclerView.ViewHolder(view) {

    //解析视图
    private val title: TextView = view.findViewById(R.id.title)
    private val subtitle: TextView = view.findViewById(R.id.subtitle)
    private val score: TextView = view.findViewById(R.id.score)
    private val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    private var pagingData: PagingData? = null


    init {
        view.setOnClickListener {
            pagingData?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    /**
     * 绑定数据
     */
    fun bind(pagingData: PagingData?) {
        this.pagingData = pagingData

        //更新UI文案
        title.text = pagingData?.title ?: "loading"
        subtitle.text = itemView.context.resources.getString(
            R.string.paging_post_subtitle,
            pagingData?.author ?: "unknown"
        )
        score.text = "${pagingData?.score ?: 0}"

        //更新图片文案
        if (pagingData?.thumbnail?.startsWith("http") == true) {
            thumbnail.visibility = View.VISIBLE
            mGlide.load(pagingData.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_insert_photo_black_48dp)
                .into(thumbnail)
        } else {
            thumbnail.visibility = View.GONE
            mGlide.clear(thumbnail)
        }
    }

    /**
     * 局部更新UI
     */
    fun updateScore(item: PagingData?) {
        pagingData = item
        score.text = "${item?.score ?: 0}"
    }

    companion object {
        /**
         * 创建ViewHolder，解析布局
         */
        fun create(
            parent: ViewGroup,
            mGlide: GlideRequests
        ): PagingItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_paging_item, parent, false)
            return PagingItemViewHolder(view, mGlide)
        }
    }
}