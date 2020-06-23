package com.android.xknowledge.ui.view.textview

import android.os.Bundle
import android.widget.TextView
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
class LineHeightActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textview_lineheight)

        //LineSpace方案，原理和API > 28 setHeight源码一样
        //参考：https://stackoverflow.com/questions/37754299/how-to-properly-set-line-height-for-android
        val textviewLineSpaceTitle = findViewById<TextView>(R.id.textview_lineheight_linespacetitle)
        val lineHeight = textviewLineSpaceTitle.paint.getFontMetricsInt(null)
        textviewLineSpaceTitle.setLineSpacing(dpToPx(32f) - lineHeight, 1f)

        val textviewLineSpace = findViewById<TextView>(R.id.textview_lineheight_linespace)
        val lineHeight2 = textviewLineSpace.paint.getFontMetricsInt(null)
        textviewLineSpace.setLineSpacing(dpToPx(32f) - lineHeight2, 1f)

        //String方案，React Native 源码防范
        //参考：https://github.com/alibaba/Virtualview-Android/blob/15662fa3428d7d8510000e60218a2426ade35672/virtualview/src/main/java/com/tmall/wireless/vaf/virtualview/view/text/NativeText.java#L364
        val textviewStringTitle = findViewById<TextView>(R.id.textview_lineheight_stringtitle)
        val spannableStringBuilder =
            LineHeightSpannableStringBuilder()
        spannableStringBuilder.setContent("T24", dpToPx(32f))
        textviewStringTitle.text = spannableStringBuilder

        val textviewString = findViewById<TextView>(R.id.textview_lineheight_string)
        val spannableStringBuilder2 =
            LineHeightSpannableStringBuilder()
        spannableStringBuilder2.setContent("去哪儿旅行\nTitle 1", dpToPx(32f))
        textviewString.text = spannableStringBuilder2
    }

    private fun dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.scaledDensity + 0.5f
    }
}
