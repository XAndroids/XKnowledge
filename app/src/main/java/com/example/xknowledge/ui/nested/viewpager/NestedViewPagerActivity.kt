package com.example.xknowledge.ui.nested.viewpager

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity
import com.google.android.material.tabs.TabLayout

/**
 * ViewPager嵌套的事件冲突处理
 */
class NestedViewPagerActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_viewpager)

        val parentTabLayout = findViewById<TabLayout>(R.id.nested_viewpager_tablelayout_parent)
        val parentViewPager = findViewById<ViewPager>(R.id.nested_viewpager_viewpager_parent)
        parentTabLayout.setupWithViewPager(parentViewPager)


    }
}
