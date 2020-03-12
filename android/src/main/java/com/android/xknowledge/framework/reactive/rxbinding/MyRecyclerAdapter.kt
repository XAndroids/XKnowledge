package com.android.xknowledge.framework.reactive.rxbinding

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R
import com.jakewharton.rxbinding2.view.RxView
import java.util.*

class MyRecyclerAdapter(private val mContext: Context, private val mDataList: ArrayList<Int>) :
    RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(
            R.layout.recycler_recycler_testitem, parent,
            false
        )

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemText = mDataList[position]
        holder.text.text = itemText.toString()
        //RecyclerView的item使用clicks()来绑定事件
        RxView.clicks(holder.text).subscribe {
            Toast.makeText(mContext, "点击${itemText}", Toast.LENGTH_SHORT).show()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.recycler_testitem_textview)
    }
}