package com.example.xknowledge.ui.event.nested.scrolling

import android.os.Bundle
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

/**
 * 该Demo演示了，使用Andorid的嵌套滑动机制，实现相关交互实现
 */
class NestedScrollingActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nestedscrolling)
    }
}
