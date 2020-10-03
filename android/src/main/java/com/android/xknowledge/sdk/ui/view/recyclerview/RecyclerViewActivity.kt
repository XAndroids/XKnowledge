package com.android.xknowledge.sdk.ui.view.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class RecyclerViewActivity : TitleActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private var changeIndex = 0
    private val deleteIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val dataSources = mutableListOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )
        val recyclerAdapter =
            RecyclerAdapter(dataSources)

        mRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_recyclerview).apply {
            layoutManager = StaggeredGridLayoutManager(1, VERTICAL)
//            Log.i("RecyclerView", "RecyclerViewActivity adapter = recyclerAdapter")
            adapter = recyclerAdapter
            addOnChildAttachStateChangeListener(object :
                RecyclerView.OnChildAttachStateChangeListener {
                override fun onChildViewDetachedFromWindow(view: View) {
                    Log.i("RecyclerView", "onChildViewDetachedFromWindow view = $view")
                }

                override fun onChildViewAttachedToWindow(view: View) {
                    Log.i("RecyclerView", "onChildViewAttachedToWindow view = $view")
                }
            })
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

    override fun onPause() {
        super.onPause()
//        Log.i("RecyclerView", "RecyclerViewActivity onPause")
        mRecyclerView.adapter = null
    }

    class RecyclerAdapter(private val dataSources: List<String>) :
        RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
        class RecyclerHolder(val button: Button) : RecyclerView.ViewHolder(button)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
            Log.i("RecyclerView", "onCreateViewHolder viewType = $viewType")
            val button: Button =
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_recycler_item,
                    parent,
                    false
                ) as Button
            return RecyclerHolder(
                button
            )
        }

        override fun getItemCount(): Int {
            return dataSources.size
        }

        override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
            //添加和Change等notify*方法只会重新绑定修改位置数据
            //notifyDataSetChanged会引起所有数据的重新绑定
            Log.i("RecyclerView", "onBindViewHolder position = $position")
            holder.button.text = dataSources[position]
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            //RecyclerView开始观察(设置)Adapter
//            Log.i("RecyclerView", "onAttachedToRecyclerView recyclerView = $recyclerView")
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            super.onDetachedFromRecyclerView(recyclerView)
            //RecyclerView停止观察Adapter
//            Log.i("RecyclerView", "onDetachedFromRecyclerView recyclerView = $recyclerView")
        }

        override fun onViewAttachedToWindow(holder: RecyclerHolder) {
            super.onViewAttachedToWindow(holder)
            //RecyclerView创建的View添加到屏幕中
            Log.i("RecyclerView", "onViewAttachedToWindow holder = $holder")
        }

        override fun onViewDetachedFromWindow(holder: RecyclerHolder) {
            super.onViewDetachedFromWindow(holder)
            //RecyclerView创建的View移出屏幕中
            Log.i("RecyclerView", "onViewDetachedFromWindow holder = $holder")
        }

        override fun onFailedToRecycleView(holder: RecyclerHolder): Boolean {
            //如果Adapter创建的ViewHolder在它的transient state没有被回收的时候，被RecyclerView调用
//            Log.i("RecyclerView", "onFailedToRecycleView holder = $holder")
            return super.onFailedToRecycleView(holder)
        }

        override fun onViewRecycled(holder: RecyclerHolder) {
            //当创建的View被Adapter复用的时候调用
//            Log.i("RecyclerView", "onViewRecycled holder = $holder")
            super.onViewRecycled(holder)
        }
    }
}
