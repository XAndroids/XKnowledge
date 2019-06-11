package com.example.xknowledge.ui.event.nested

import android.os.Bundle
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

class ScrollingActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
    }
}
