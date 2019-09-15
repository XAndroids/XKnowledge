package com.example.xknowledge.test.robolectric

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

class RobolectricActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robolectric)

        val loginButton = findViewById<Button>(R.id.robolectric_button_login)
        loginButton.setOnClickListener {
            val intent = Intent(this@RobolectricActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
