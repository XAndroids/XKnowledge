package com.example.xknowledge.hybrid.jsinvoke

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

class JSInvokeActivity : TitleActivity() {
    private lateinit var mContainerWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jsinvoke)

        mContainerWebView = findViewById(R.id.jsinvoke_webview_container)
        val webSettings = mContainerWebView.settings
        //设置与Js交互的权限
        webSettings.javaScriptEnabled = true
        //设置允许Js弹窗
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        mContainerWebView.loadUrl("file:///android_asset/jsinvoke/jsinvoke.html")

        val version = Build.VERSION.SDK_INT
//        if (version < 18) {
        //由于设置了弹窗检验调用结果,所以需要支持js对话框
        //webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        //通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        mContainerWebView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                val builder = AlertDialog.Builder(this@JSInvokeActivity)
                builder.setTitle("Alert")
                builder.setMessage(message)
                builder.setPositiveButton(android.R.string.ok) { _, _ ->
                    result?.confirm()
                }
                builder.setCancelable(false)
                builder.create().show()
                return true
            }

            override fun onJsPrompt(
                view: WebView?,
                url: String?,
                message: String?,
                defaultValue: String?,
                result: JsPromptResult?
            ): Boolean {
                val uri = Uri.parse(url)
                if (uri?.scheme.equals("js")) {
                    if (uri?.authority.equals("webview")) {
                        val arg1 = uri.getQueryParameter("arg1")
                        Toast.makeText(this@JSInvokeActivity, arg1, Toast.LENGTH_LONG).show()
                    }

                    return true
                }

                return super.onJsPrompt(view, url, message, defaultValue, result)
            }
        }

        //注意调用的JS方法名要对应上 调用javascript的callJS()方法
        //注意JS代码调用一定要在 onPageFinished（） 回调之后才能调用，否则不会调用。
        mContainerWebView.postDelayed({ mContainerWebView.loadUrl("javascript:callJS()"); }, 2000)
//        } else {
//            mContainerWebView.postDelayed({
//                mContainerWebView.evaluateJavascript(
//                    "javascript:callJS()"
//                ) { value ->
//                    val builder = AlertDialog.Builder(this@JSInvokeActivity)
//                    builder.setTitle("Alert")
//                    builder.setMessage(value)
//                    builder.setCancelable(true)
//                    builder.create().show()
//                }
//            }, 2000)
//        }

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        mContainerWebView.addJavascriptInterface(AndroidtoJs(this), "test")

        mContainerWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                val uri = Uri.parse(url)
                if (uri?.scheme.equals("js")) {
                    if (uri?.authority.equals("webview")) {
                        val arg1 = uri.getQueryParameter("arg1")
                        Toast.makeText(this@JSInvokeActivity, arg1, Toast.LENGTH_LONG).show()
                    }

                    return true
                }
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
    }
}
