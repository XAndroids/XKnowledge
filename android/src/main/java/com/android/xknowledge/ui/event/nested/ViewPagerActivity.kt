package com.android.xknowledge.ui.event.nested

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.google.android.material.tabs.TabLayout

/**
 * ViewPager嵌套的事件冲突处理
 */
class ViewPagerActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        val parentTabLayout = findViewById<TabLayout>(R.id.viewpager_tablelayout_parent)
        val parentViewPager = findViewById<ViewPager>(R.id.viewpager_viewpager_parent)
        parentTabLayout.setupWithViewPager(parentViewPager)


    }
}
