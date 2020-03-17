package com.android.xknowledge.ui.link

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class DeepLinkActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deeplink)
    }
}