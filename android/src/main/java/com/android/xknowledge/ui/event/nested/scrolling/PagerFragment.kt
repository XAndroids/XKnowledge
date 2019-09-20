package com.android.xknowledge.ui.event.nested.scrolling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R

class PagerFragment : Fragment() {
    companion object {
        internal const val TITLE = "title"
    }

    private lateinit var itemArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.takeIf {
            it.containsKey(TITLE)
        }?.apply {
            val title = getString(TITLE)?.toString()
            itemArray = Array(50) { i -> "$title->item$i" }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nestedscrolling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.fragment_nestedscrolling_contentrecyclerview).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PagerFragment.context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = ContentAdapter(this@PagerFragment.itemArray)
        }
    }

    class ContentAdapter(private val itemArray: Array<String>) :
        RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {
        class ContentViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
            val textView =
                LayoutInflater.from(parent.context).inflate(
                    R.layout.fragment_nestedscrolling_item,
                    parent,
                    false
                ) as TextView
            return ContentViewHolder(textView)
        }

        override fun getItemCount(): Int = itemArray.size

        override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
            holder.textView.text = itemArray[position]
        }
    }
}