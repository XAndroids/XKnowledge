package com.example.xknowledge.ui.event.nested

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity
import com.example.xknowledge.ui.view.recyclerview.RecyclerViewActivity

class RecyclerViewActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view2)

        findViewById<RecyclerView>(R.id.recyclerview_recyclerview_parent).apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = RecyclerAdapter1(
                context,
                listOf(
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "A",
                    "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"
                )
            )
        }
    }

    class RecyclerAdapter1(private val context: Context, private val dataSource: List<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        class RecyclerHolder1(var textView: TextView) : RecyclerView.ViewHolder(textView)
        class RecyclerHolder2(var recyclerView: RecyclerView) : RecyclerView.ViewHolder(recyclerView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                1 -> {
                    val textView = LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_recycler_item2,
                        parent, false
                    ) as TextView
                    RecyclerHolder1(textView)
                }
                else -> {
                    val recyclerView = LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_recycler_item3,
                        parent,
                        false
                    ) as RecyclerView
                    RecyclerHolder2(recyclerView)
                }
            }

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is RecyclerHolder1 -> {
                    holder.textView.text = dataSource[position]
                }
                is RecyclerHolder2 -> {
                    holder.recyclerView.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
                        adapter = RecyclerViewActivity.RecyclerAdapter(
                            listOf(
                                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "A",
                                "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"
                            )
                        )
                    }
                }
            }
        }

        override fun getItemCount(): Int {
            return dataSource.size
        }

        override fun getItemViewType(position: Int): Int {
            return when (position) {
                3 -> 0
                else -> 1
            }
        }
    }
}
