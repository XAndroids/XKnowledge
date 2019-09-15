package com.example.xknowledge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * TitleActivity，获取传入的的标题，设置在ActionBar上
 */
open class TitleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = intent.getStringExtra(TITLE_NAME) ?: "XKnowledge"
    }

    companion object {
        const val TITLE_NAME = "title"
    }
}
