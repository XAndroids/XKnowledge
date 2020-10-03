package com.android.xknowledge.sdk.storage.sharedpreferences

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * SharedPreferences的基本使用：https://blog.csdn.net/u014651216/article/details/50949301
 * SharedPreferences的原理分析：https://www.jianshu.com/p/f7289e801a7f
 */
class SharedPreferencesActivity : TitleActivity() {
    lateinit var mWriteEdittext: EditText
    lateinit var mCommitButton: Button
    lateinit var mApplyButton: Button
    lateinit var mReadTextView: TextView
    lateinit var mReadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        mWriteEdittext = findViewById(R.id.sharedpreferences_edittext_write)
        mCommitButton = findViewById(R.id.sharedpreferences_button_commit)
        mApplyButton = findViewById(R.id.sharedpreferences_button_apply)
        mReadTextView = findViewById(R.id.sharedpreferences_textview_read)
        mReadButton = findViewById(R.id.sharedpreferences_button_read)

        val preference = getSharedPreferences("sharepreference", Context.MODE_PRIVATE)    //sharepreference存储文件名称:sharepreference.xml
        val editor = preference.edit()
        mCommitButton.setOnClickListener {
            editor.putString("write", mWriteEdittext.text.toString())
            editor.commit()    //commit同步
        }

        mApplyButton.setOnClickListener {
            editor.putString("write", mWriteEdittext.text.toString())
            editor.apply()    //apply()异步
        }
        mReadButton.setOnClickListener {
            mReadTextView.text = preference.getString("write", "default")
        }
    }
}
