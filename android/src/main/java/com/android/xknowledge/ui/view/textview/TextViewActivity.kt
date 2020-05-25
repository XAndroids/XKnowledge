package com.android.xknowledge.ui.view.textview

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * 验证TextView行高的设置：
 * android:lineHeight：文案基线之间的距离，数值等于行高，单位dp，SDK>28才支持；
 * android:lineSpacingExtra：默认行间距之外增加的间距，单位dp；
 * android:lineSpacingMultiplier：默认行间距的倍数，1.5倍等；
 * 其它：
 * lineHeight覆盖lineSpacingExtra、lineSpacingMultiplier；
 * lineSpacingMultiplier先倍数，在增加lineSpacingExtra；
 */
class TextViewActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)
    }
}
