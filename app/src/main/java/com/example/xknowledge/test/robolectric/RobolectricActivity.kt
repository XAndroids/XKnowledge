package com.example.xknowledge.test.robolectric

import android.os.Bundle
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

class RobolectricActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robolectric)
    }
}
