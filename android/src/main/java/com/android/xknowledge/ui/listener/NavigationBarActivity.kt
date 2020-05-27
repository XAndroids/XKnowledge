package com.android.xknowledge.ui.listener

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import com.android.xknowledge.R

/**
 * 监听虚拟导航栏的显示和隐藏
 * Android系统提供的监听方式window.decorView.setOnSystemUiVisibilityChangeListener，在不同的手机厂商不一定生效
 * 通过监听页面RootView的高度变化，来间接监听虚拟导航栏的显示和隐藏
 */
class NavigationBarActivity : Activity() {
    private lateinit var mCustomGlobalLayoutListener: CustomGlobalLayoutListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigationbar)
        findViewById<LinearLayout>(R.id.navigation_linearlayout_root).addOnAttachStateChangeListener(
            object :
                View.OnAttachStateChangeListener {
                override fun onViewDetachedFromWindow(v: View?) {
                    Log.i("NavigationBarActivity", "onViewDetachedFromWindow")
                    v?.viewTreeObserver?.removeOnGlobalLayoutListener(
                        getCustomGlobalLayoutListener(
                            v
                        )
                    )
                }

                override fun onViewAttachedToWindow(v: View?) {
                    Log.i("NavigationBarActivity", "onViewAttachedToWindow")
                    v?.viewTreeObserver?.addOnGlobalLayoutListener(getCustomGlobalLayoutListener(v))
                }
            })
    }

    private fun getCustomGlobalLayoutListener(rootView: View): CustomGlobalLayoutListener {
        if (!this::mCustomGlobalLayoutListener.isInitialized) {
            mCustomGlobalLayoutListener = CustomGlobalLayoutListener(rootView)
        }
        return mCustomGlobalLayoutListener
    }


    class CustomGlobalLayoutListener(private val rootView: View) :
        ViewTreeObserver.OnGlobalLayoutListener {
        private var rootViewHeight = 0

        override fun onGlobalLayout() {
            Log.i(
                "NavigationBarActivity",
                "onGlobalLayout,rootViewHeight = $rootViewHeight , rootView.height = ${rootView.height}, realHeight = ${ScreamUtils.getRealHeight(
                    rootView.context
                )}"
            )
            if (rootViewHeight != rootView.height) {
                rootViewHeight = rootView.height
                if (rootView.height == ScreamUtils.getRealHeight(rootView.context)) {
                    Log.i("NavigationBarActivity", "Bar Hide")
                } else {
                    Log.i("NavigationBarActivity", "Bar Show")
                }
            }
        }
    }
}
