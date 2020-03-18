package com.android.xknowledge.ui.statusbar

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class ImmersiveActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //沉浸式设置相关逻辑
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        super.onCreate(savedInstanceState)
        Log.i("Immersive", "onCreate")
        setContentView(R.layout.activity_immersive)
    }

    override fun onResume() {
        super.onResume()
        Log.i("Immersive", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Immersive", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        //在魅族手机上，从无导航栏切换到导航栏Activity会重建
        Log.i("Immersive", "onDestroy")
    }
}