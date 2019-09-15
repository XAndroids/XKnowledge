package com.example.xknowledge.component.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity
import com.example.xknowledge.tool.TaskTools

class LifeCycleActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("LifeCycle", "LifeCycleActivity_onCreate")
        setContentView(R.layout.activity_lifecycle)

        val toOneButton = findViewById<Button>(R.id.lifecycler_button_toone)
        toOneButton.setOnClickListener {
            val intent = Intent(this@LifeCycleActivity, ActivityOne::class.java)
            startActivity(intent)
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
