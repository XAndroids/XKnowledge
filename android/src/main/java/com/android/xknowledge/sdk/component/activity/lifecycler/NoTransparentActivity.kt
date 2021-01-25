package com.android.xknowledge.sdk.component.activity.lifecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.xknowledge.R

/**
 * 非透明Activity，跳转不影响生命周期
 */
class NoTransparentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("LifeCycle", "ActivityOne_onCreate")
        setContentView(R.layout.activity_notransparent)
    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle", "NoTransparent_onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LifeCycle", "NoTransparent_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LifeCycle", "NoTransparent_onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle", "NoTransparent_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "NoTransparent_onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle", "NoTransparent_onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("LifeCycle", "NoTransparent_onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("LifeCycle", "NoTransparent_onRestoreInstanceState")
    }
}
