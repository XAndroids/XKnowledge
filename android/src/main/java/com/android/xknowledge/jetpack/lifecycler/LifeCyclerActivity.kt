package com.android.xknowledge.jetpack.lifecycler

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class LifeCyclerActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycler)
        lifecycle.addObserver(MyObserver());
    }
}
