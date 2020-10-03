package com.android.xknowledge.sdk.ui.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.DialogFragment
import com.android.xknowledge.R

class WebviewDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Dialog的透明背景
        setStyle(STYLE_NORMAL, R.style.Dialog_FullScreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return WebView(context).apply {
            //WebView的透明背景
            setBackgroundColor(Color.parseColor("#00000000"))
            loadUrl("file:///android_asset/web/transport.html")
        }
    }
}