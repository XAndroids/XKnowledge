package com.android.xknowledge.open.reactive.rxbinding

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

//参考《RXJava 2.x实战》,Disposble和Transformer章节
/**
 * 使用rxbinding技术，实现表单提交功能
 */
class FormActivity : TitleActivity() {
    private var mMessage: String = ""

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val phoneEdittext = findViewById<EditText>(R.id.form_phone_edittext)
        val passwordEdittext = findViewById<EditText>(R.id.form_password_edittext)
        val loginTextView = findViewById<TextView>(R.id.form_login_textview)

        //通过RxTextView.textChange()获取输入框输入事件的数据源
        val phoneObservable = RxTextView.textChanges(phoneEdittext)
        val passwordObservable = RxTextView.textChanges(passwordEdittext)

        //combineLatest操作符：当phoneObservable和passwordObservabler任何输入发射数据的时候，通过函数更新loginTextView
        //背景图片，并且校验电话号码和密码，发送校验结果message
        Observable.combineLatest(phoneObservable, passwordObservable,
            BiFunction<CharSequence, CharSequence, String> { phone, password ->
                if (phone.isNotEmpty() || password.isNotEmpty()) {
                    loginTextView.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_login_pressed))
                } else {
                    loginTextView.setBackgroundDrawable(resources.getDrawable(R.drawable.shape_login_normal))
                }

                var message: String = ""
                when {
                    phone.isEmpty() -> {
                        message = "手机号码不能为空"
                    }
                    phone.length != 11 -> {
                        message = "手机好吗需要11位"
                    }
                    password.isEmpty() -> {
                        message = "密码不能为空"
                    }
                }

                message
            }).subscribe {
            mMessage = it
        }

        RxView.clicks(loginTextView).subscribe {
            if (mMessage.isEmpty()) {
                Toast.makeText(this, mMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
            }
        }
    }
}