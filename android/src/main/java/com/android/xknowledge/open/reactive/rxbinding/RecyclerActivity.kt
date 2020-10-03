package com.android.xknowledge.open.reactive.rxbinding

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import java.util.*

//参考《RxJava 2.x实战》 Rxbinding的使用章节
/**
 * RXBinding提供了RecyclerView的支持
 */
class RecyclerActivity : TitleActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val testRecyclerView = findViewById<RecyclerView>(R.id.recycler_test_recyclerview)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        testRecyclerView.layoutManager = linearLayoutManager

        //RxRecyclerView提供了几个状态的观察
        //scrollStateChanges:观察RecyclerView的滚动状态
        RxRecyclerView.scrollStateChanges(testRecyclerView).subscribe {
            Log.i("RecyclerActivity", "scrollState = $it")
        }

        val itemList = ArrayList<Int>()
        for (i in 0..40) {
            itemList.add(i)
        }

        val recycleAdapter = MyRecyclerAdapter(this, itemList)
        testRecyclerView.adapter = recycleAdapter
    }
}