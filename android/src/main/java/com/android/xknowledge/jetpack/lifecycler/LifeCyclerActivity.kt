package com.android.xknowledge.jetpack.lifecycler

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * 参考：https://chiclaim.blog.csdn.net/article/details/104189041
 */
class LifeCyclerActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycler)
        lifecycle.addObserver(MyObserver());
    }
}
