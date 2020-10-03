package com.android.xknowledge.sdk.ui.layout

import android.app.Activity
import android.os.Bundle
import com.android.xknowledge.R

/**
 * include标签的使用：复用布局
 * 原理：解析include标签中的布局，添加到include标签父布局中；
 * 参考：https://blog.csdn.net/bboyfeiyu/article/details/45869393
 */
class IncludeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_include)
    }
}
