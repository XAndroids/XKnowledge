package com.android.xknowledge.open.reactive.rxbinding

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

//参考《RXJava 2.x实战》,Disposble和Transformer章节
/**
 * 实现验证码功能
 */
class VerifyActivity : TitleActivity() {
    private val MAX_COUNT_TIME: Long = 60

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)
        val getTextView = findViewById<TextView>(R.id.verify_get_textview)

        //RxView.clicks获取点击事件数据源
        RxView.clicks(getTextView).throttleFirst(MAX_COUNT_TIME, TimeUnit.SECONDS)
            .flatMap(Function<Any, ObservableSource<Long>> {
                //flatMap操作符：将点击事件转换成Observable.interval()连续60s发送的数据源
                RxView.enabled(getTextView).accept(false)
                RxTextView.text(getTextView).accept("剩余$MAX_COUNT_TIME 秒")

                Observable.interval(1, TimeUnit.SECONDS, Schedulers.io()).take(MAX_COUNT_TIME)
            }).map {
                //map操作符：定时数据源，转换成倒计时时间
                MAX_COUNT_TIME - (it + 1)
            }.observeOn(AndroidSchedulers.mainThread()).subscribe {
                //observeOn操作符：切换到主线程更新UI
                if (it == 0L) {
                    RxView.enabled(getTextView).accept(true)
                    RxTextView.text(getTextView).accept("获取验证码")
                } else {
                    RxTextView.text(getTextView).accept("剩余 $it 秒")
                }
            }
    }
}