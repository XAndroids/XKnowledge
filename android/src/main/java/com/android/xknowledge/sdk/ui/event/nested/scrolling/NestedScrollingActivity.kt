package com.android.xknowledge.sdk.ui.event.nested.scrolling

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.google.android.material.tabs.TabLayout

/**
 * 该Demo演示了，使用Andorid的嵌套滑动机制，实现相关交互实现
 */
class NestedScrollingActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nestedscrolling)

        val viewPagerContent = findViewById<ViewPager>(R.id.nestedscrolling_viewpager_content)
        val tablayoutTitle = findViewById<TabLayout>(R.id.nestedscrolling_tablayout_title)
        tablayoutTitle.setupWithViewPager(viewPagerContent)
        viewPagerContent.adapter = PagerAdaper(supportFragmentManager, arrayOf("Pager1", "Pager2", "Pager3"))
    }
}
