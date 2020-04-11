package com.android.xknowledge.framework.aspectj

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.android.xknowledge.R
import com.android.xknowledge.XApplication
import com.android.xknowledge.framework.aspectj.login.CheckLogin

class Login2Activity : AppCompatActivity(), OnClickListener, OnCheckedChangeListener {
    private var radioGroup: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        radioGroup = findViewById(R.id.login2_login_radiogroup)
        radioGroup!!.setOnCheckedChangeListener(this)

        findViewById<TextView>(R.id.login2_attention_textview).setOnClickListener(this)
        findViewById<TextView>(R.id.login2_footprint_textview).setOnClickListener(this)
        findViewById<TextView>(R.id.login2_shoppingcart_textview).setOnClickListener(this)
    }

    override fun onCheckedChanged(group: RadioGroup, @IdRes checkedId: Int) {
        when (checkedId) {
            R.id.login2_login_radiobutton -> XApplication.isLogin = true
            R.id.login2_nologin_radiobutton -> XApplication.isLogin = false
        }
    }

    @CheckLogin
    override fun onClick(v: View) {
        var result = ""
        when (v.id) {
            R.id.login2_attention_textview -> result = "已登录,点击了我的关注"
            R.id.login2_footprint_textview -> result = "已登录,点击了我的足迹"
            R.id.login2_shoppingcart_textview -> result = "已登录,点击了我的购物车"
        }

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}