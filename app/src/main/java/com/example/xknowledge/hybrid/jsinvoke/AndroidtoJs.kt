package com.example.xknowledge.hybrid.jsinvoke

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class AndroidtoJs(private val mContext: Context) {

    @JavascriptInterface
    fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }
}