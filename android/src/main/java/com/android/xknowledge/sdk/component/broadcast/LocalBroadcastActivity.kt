package com.android.xknowledge.sdk.component.broadcast

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class LocalBroadcastActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localbroadcast)
    }
}
