package com.android.xknowledge.component.activity.lifecycler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.android.xknowledge.tool.TaskTools

class LifeCycleActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("LifeCycle", "LifeCycleActivity_onCreate")
        setContentView(R.layout.activity_lifecycle)

        val toOneButton = findViewById<Button>(R.id.lifecycler_button_toone)
        toOneButton.setOnClickListener {
            Intent().apply {
                action = "com.android.xknowledge.component.activity.lifecycler.ActivityOne"
                startActivity(this)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle", "LifeCycleActivity_onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LifeCycle", "LifeCycleActivity_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LifeCycle", "LifeCycleActivity_onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle", "LifeCycleActivity_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle", "LifeCycleActivity_onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LifeCycle", "LifeCycleActivity_onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i("LifeCycle", "LifeCycleActivity_onSaveInstanceState " + TaskTools.geStaskToptActivity(this))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("LifeCycle", "LifeCycleActivity_onRestoreInstanceState")
    }
}
