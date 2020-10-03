package com.android.xknowledge.sdk.component.activity.lifecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.xknowledge.R

class ActivityOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("LifeCycle", "ActivityOne_onCreate")
        setContentView(R.layout.activity_one)
    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle", "ActivityOne_onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LifeCycle", "ActivityOne_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LifeCycle", "ActivityOne_onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle", "ActivityOne_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "ActivityOne_onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle", "ActivityOne_onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("LifeCycle", "ActivityOne_onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("LifeCycle", "ActivityOne_onRestoreInstanceState")
    }
}
