package com.android.xknowledge.framework.inject

import android.os.Bundle
import android.widget.TextView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class InjectActivity : TitleActivity() {
    @InjectView(R.id.inject_textview)
    private val textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inject)

        InjectUtils.injectView(this);
        textView?.text = "控件初始化成功"
    }
}