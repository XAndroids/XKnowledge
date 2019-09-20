package com.android.xknowledge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ListActivity : TitleActivity() {
    private lateinit var mRecyclerView: RecyclerView

    abstract fun getMyListItemList(): List<ListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        mRecyclerView = findViewById<RecyclerView>(R.id.list_recyclerview).apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = ListAdapter(getMyListItemList())
        }
    }

    class ListAdapter(private val myListItemList: List<ListItem>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>(),
        View.OnClickListener {

        class MyViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
            val titleTextView: TextView = rootView.findViewById(R.id.list_item_title)
            val subTitleTextView: TextView = rootView.findViewById(R.id.list_item_subtitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val rootView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return MyViewHolder(rootView)
        }

        override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            viewHolder.titleTextView.text = myListItemList[position].title
            viewHolder.subTitleTextView.text = myListItemList[position].subTitle
            viewHolder.rootView.tag = position
            viewHolder.rootView.setOnClickListener(this)
        }

        override fun getItemCount(): Int = myListItemList.size

        override fun onClick(view: View?) {
            val listItem = myListItemList[view?.tag as Int]
            val intent = Intent(view.context, listItem.pageClass)
            intent.putExtra(TITLE_NAME, listItem.title)
            view.context.startActivity(intent)
        }
    }

    data class ListItem(val title: String, val subTitle: String, val pageClass: Class<*>)
}


