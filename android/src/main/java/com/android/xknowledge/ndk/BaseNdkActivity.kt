package com.android.xknowledge.ndk

import android.os.Bundle
import android.util.Log
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class BaseNdkActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_ndk)
        Log.i("BaseNdkActivity", "ndk success:" + NativeLib().stringFromJNI())
    }

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}
