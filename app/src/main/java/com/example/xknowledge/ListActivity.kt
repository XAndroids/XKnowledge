package com.example.xknowledge

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xknowledge.TitleActivity.Companion.TITLE_NAME

abstract class ListActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView

    abstract fun getMyListItemList(): List<ListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        mRecyclerView = findViewById<RecyclerView>(R.id.list_recyclerview).apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addItemDecoration(ListItemDecoration(30))
            adapter = ListAdapter(getMyListItemList())
        }
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
            val intent = Intent(view.context, listItem.pageClass)
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


