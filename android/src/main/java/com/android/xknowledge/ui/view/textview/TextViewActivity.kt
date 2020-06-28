package com.android.xknowledge.ui.view.textview

import com.android.xknowledge.ListActivity

class TextViewActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LineHeight", "文案高度相关", LineHeightActivity::class.java),
            ListItem("Ellipsize", "文案省略相关", EllipsizeActivity::class.java),
            ListItem("IconFont", "IconFont相关", IconFontActivity::class.java)
        )
    }
}
