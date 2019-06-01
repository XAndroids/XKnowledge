package com.example.xknowledge

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

abstract class ListActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView

    abstract fun getMyListItemList(): List<ListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportActionBar?.title = intent.getStringExtra(TITLE_NAME) ?: "XKnowledge"

        mRecyclerView = findViewById<RecyclerView>(R.id.list_recyclerview).apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addItemDecoration(ListItemDecoration(30))
            adapter = ListAdapter(getMyListItemList())
        }
    }

    companion object {
        const val TITLE_NAME = "title"
    }

    class ListAdapter(private val myListItemList: List<ListItem>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>(),
        View.OnClickListener {

        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val textView =
                LayoutInflater.from(parent.context).inflate(R.layout.main_recycler_item, parent, false) as TextView
            return MyViewHolder(textView)
        }

        override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            viewHolder.textView.text = myListItemList[position].title
            viewHolder.textView.tag = position
            viewHolder.textView.setOnClickListener(this)
        }

        override fun getItemCount(): Int = myListItemList.size

        override fun onClick(view: View?) {
            val listItem = myListItemList[view?.tag as Int]
            val intent = Intent(view?.context, listItem.pageClass)
            intent.putExtra(TITLE_NAME, listItem.title)
            view.context.startActivity(intent)
        }
    }

    class ListItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
            outRect.top = space
        }
    }

    data class ListItem(val title: String, val pageClass: Class<*>)

}


