package com.android.xknowledge.sdk.component.broadcast

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * 广播接受者分类：
 * 普通、有序、粘性、本地、系统
 * 参考：
 * https://blog.csdn.net/carson_ho/article/details/52973504
 * https://www.jianshu.com/p/0b3a7b35d76d
 */
class BroadcastClassifyActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_classify)
    }
}
