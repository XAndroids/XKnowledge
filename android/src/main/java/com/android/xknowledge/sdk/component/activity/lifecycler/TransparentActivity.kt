package com.android.xknowledge.sdk.component.activity.lifecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * 透明Activity主题Activity，跳转该页面，上一个页面生命周期不执行onStop和onStart
 */
class TransparentActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("LifeCycle", "TransparentActivity_onCreate")
        setContentView(R.layout.activity_transparent)
    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle", "TransparentActivity_onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LifeCycle", "TransparentActivity_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LifeCycle", "TransparentActivity_onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle", "TransparentActivity_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "TransparentActivity_onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle", "TransparentActivity_onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("LifeCycle", "TransparentActivity_onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("LifeCycle", "TransparentActivity_onRestoreInstanceState")
    }
}