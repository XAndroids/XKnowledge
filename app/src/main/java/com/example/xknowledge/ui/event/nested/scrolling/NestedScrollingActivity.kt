package com.example.xknowledge.ui.event.nested.scrolling

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity
import com.google.android.material.tabs.TabLayout

/**
 * 该Demo演示了，使用Andorid的嵌套滑动机制，实现相关交互实现
 */
class NestedScrollingActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nestedscrolling)

        val contentViewPager = findViewById<ViewPager>(R.id.activity_nestedscrolling_contentviewpager)
        val titleTablayout = findViewById<TabLayout>(R.id.activity_nestedscrolling_titletablayout)
        titleTablayout.setupWithViewPager(contentViewPager)
        contentViewPager.adapter = PagerAdaper(supportFragmentManager, arrayOf("Pager1", "Pager2", "Pager3"))
    }
}
