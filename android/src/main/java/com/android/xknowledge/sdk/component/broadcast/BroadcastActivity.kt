package com.android.xknowledge.sdk.component.broadcast

import com.android.xknowledge.ListActivity

class BroadcastActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LocalBroadcast", "本地广播相关", LocalBroadcastActivity::class.java),
            ListItem("StaticBroadCast", "静态广播相关", StaticBroadcastActivity::class.java)
        )
    }
}