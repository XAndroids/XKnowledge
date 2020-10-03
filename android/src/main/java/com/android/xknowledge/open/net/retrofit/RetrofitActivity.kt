package com.android.xknowledge.open.net.retrofit

import com.android.xknowledge.ListActivity

/**
 * Retrofit使用相关
 */
class RetrofitActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Get请求", "Get请求相关", GetRequestActivity::class.java),
            ListItem("Post请求", "Post请求相关", PostRequestActivity::class.java)
        )
    }
}