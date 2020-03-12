package com.android.xknowledge.framework.reactive.rxbinding

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.functions.Consumer

//参考《RXJava 2.x实战》,Disposble和Transformer章节
/**
 * rxbinding点击、长按、防止重复点击实现
 */
class ClickActivity : TitleActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click)

        val clickButton = findViewById<Button>(R.id.click_click_button)
        val longClickButton = findViewById<Button>(R.id.click_longclick_button)
        val noMultiClickButton = findViewById<Button>(R.id.click_nomulticlick_button);
        val multiClickButton = findViewById<Button>(R.id.click_multiclick_button)

        //点击事件
        RxView.clicks(clickButton).subscribe {
            Toast.makeText(this, "${it.javaClass},click", Toast.LENGTH_SHORT)
                .show()
        }

        //长按点击事件
        RxView.longClicks(longClickButton).subscribe {
            Toast.makeText(this, "${it.javaClass},longclick", Toast.LENGTH_SHORT)
                .show()
        }

        //防止重复点击
        RxView.clicks(noMultiClickButton).compose(RxUtils.useRxViewTransformer()).subscribe {
            Toast.makeText(this, "${it.javaClass},noMultiClick", Toast.LENGTH_SHORT)
                .show()
        }

        //多次监听
        val clickObservable =
            RxView.clicks(multiClickButton).compose(RxUtils.useRxViewTransformer()).share()
        clickObservable.subscribe {
            Toast.makeText(this, "${it.javaClass},first click", Toast.LENGTH_SHORT).show()
        }
        clickObservable.subscribe {
            Toast.makeText(this, "${it.javaClass},second click", Toast.LENGTH_SHORT).show()
        }
    }
}