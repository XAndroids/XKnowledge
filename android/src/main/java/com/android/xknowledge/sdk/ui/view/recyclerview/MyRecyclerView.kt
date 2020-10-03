package com.android.xknowledge.sdk.ui.view.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerView(context: Context, attributeSet: AttributeSet?) :
    RecyclerView(context, attributeSet) {

    override fun onChildAttachedToWindow(child: View) {
        super.onChildAttachedToWindow(child)
        Log.i("RecyclerView", "onChildAttachedToWindow child = $child")
    }

    override fun onChildDetachedFromWindow(child: View) {
        super.onChildDetachedFromWindow(child)
        Log.i("RecyclerView", "onChildDetachedFromWindow child = $child")
    }
}