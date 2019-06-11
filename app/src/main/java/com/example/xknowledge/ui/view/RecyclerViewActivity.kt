package com.example.xknowledge.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

class RecyclerViewActivity : TitleActivity() {
    private var changeIndex = 0
    private val deleteIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val dataSources = mutableListOf("A", "B", "C", "D", "E", "F", "G")
        val recyclerAdapter = RecyclerAdapter(dataSources)

        findViewById<RecyclerView>(R.id.recyclerview_recyclerview).apply {
            layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
            adapter = recyclerAdapter
        }

        findViewById<Button>(R.id.recyclerview_insert).setOnClickListener {
            dataSources.add(dataSources.size - 1, "Add")
            recyclerAdapter.notifyItemInserted(dataSources.size - 2)
        }

        findViewById<Button>(R.id.recyclerview_delete).setOnClickListener {
            if (dataSources.size > deleteIndex) {
                dataSources.removeAt(deleteIndex)
                recyclerAdapter.notifyItemRemoved(deleteIndex)
            }
        }

        findViewById<Button>(R.id.recyclerview_change).setOnClickListener {
            if (changeIndex < dataSources.size) {
                dataSources[changeIndex] = "Change"
                recyclerAdapter.notifyItemChanged(changeIndex)
                recyclerAdapter.notifyDataSetChanged()
                changeIndex++
            }
        }
    }

    class RecyclerAdapter(private val dataSources: List<String>) :
        RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
        class RecyclerHolder(val button: Button) : RecyclerView.ViewHolder(button)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
            val button: Button =
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_recycler_item, parent, false) as Button
            return RecyclerHolder(button)
        }

        override fun getItemCount(): Int {
            return dataSources.size
        }

        override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
            //添加和Change等notify*方法只会重新绑定修改位置数据
            //notifyDataSetChanged会引起所有数据的重新绑定
            Log.i("RecyclerView", "onBindViewHolder position = " + position)
            holder.button.text = dataSources[position]
        }


    }
}
