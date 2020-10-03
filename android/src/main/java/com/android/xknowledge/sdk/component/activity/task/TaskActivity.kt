package com.android.xknowledge.sdk.component.activity.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.xknowledge.R
import com.android.xknowledge.sdk.component.activity.ActivityActivity

/**
 * 实现在ViewGroup中获取当前Activity栈的栈顶的相关信息
 */
class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        findViewById<Button>(R.id.taskactivity_button_next).setOnClickListener {
            val intent = Intent(this@TaskActivity, ActivityActivity::class.java)
            startActivity(intent)
        }
    }
}
