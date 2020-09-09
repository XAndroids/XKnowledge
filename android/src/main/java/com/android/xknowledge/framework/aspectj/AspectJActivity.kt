package com.android.xknowledge.framework.aspectj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * AspectJ实现权限检查，登录和网络等切面编程
 * 参考：https://www.jianshu.com/p/9fb07b2596f7
 * 注意！！！目前配置下，在Kotlin中无法生效
 */
class AspectJActivity : TitleActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aspectj)

        findViewById<Button>(R.id.aspectj_permission_button).setOnClickListener(this)
        findViewById<Button>(R.id.aspectj_network_button).setOnClickListener(this)
        findViewById<Button>(R.id.aspectj_login_button).setOnClickListener(this)
    }

    private fun checkPermission() {
        Log.i("tag", "检查权限")
    }

    private fun checkNetwork() {
        Log.i("tag", "开始检查网络状况")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.aspectj_permission_button -> checkPermission()
            R.id.aspectj_network_button -> checkNetwork()
            R.id.aspectj_login_button -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}