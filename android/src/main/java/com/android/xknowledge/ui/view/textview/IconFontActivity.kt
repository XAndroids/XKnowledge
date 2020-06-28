package com.android.xknowledge.ui.view.textview

import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class IconFontActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icon_font)
        val typeface = Typeface.createFromAsset(assets, "fonts/iconfont.ttf")
        val iconfont1 = findViewById<TextView>(R.id.iconfont_textview_1)
        iconfont1.typeface = typeface
        iconfont1.text = "\u880b"

//        val typeface2 = Typeface.createFromAsset(assets, "fonts/q_design_font.ttf")
        val iconfont2 = findViewById<TextView>(R.id.iconfont_textview_2)
//        iconfont2.typeface = typeface2
        iconfont2.text = "\uea38"
    }
}
