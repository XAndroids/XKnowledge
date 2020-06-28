package com.android.xknowledge.ui.layout

import android.app.Activity
import android.os.Bundle
import com.android.xknowledge.R

/**
 * merge标签的使用：减少include布局层级
 * 原理：解析merge标签中的元素，添加到include标签父布局中，避免引入额外层级；
 * 参考：https://blog.csdn.net/bboyfeiyu/article/details/45869393
 */
class MergeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merge)
    }
}
