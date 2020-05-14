package com.android.xknowledge.framework.fresco

import com.android.xknowledge.ListActivity

class FrescoActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("List", "实现使用Fresco加载列表图片", ImageListActivity::class.java),
            ListItem("Gif", "实现使用Fresco加载Gif", GifActivity::class.java),
            ListItem("GifList", "实现使用Fresco加载Gif图片列表", GifListActivity::class.java)
        )
    }
}