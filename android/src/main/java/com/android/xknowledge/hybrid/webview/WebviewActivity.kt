package com.android.xknowledge.hybrid.webview

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.view.ViewGroup
import android.webkit.*
import android.webkit.WebSettings.LOAD_NO_CACHE
import android.widget.LinearLayout
import android.widget.TextView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class WebviewActivity : TitleActivity() {
    companion object {
        const val TAG: String = "WebviewTag"
    }

    private lateinit var mWebView: WebView
    private lateinit var mRootLinearLayout: LinearLayout
    private lateinit var mTitleTextView: TextView
    private lateinit var mBeginloadingTextView: TextView
    private lateinit var mLoadingTextView: TextView
    private lateinit var mEndLoadingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        mRootLinearLayout = findViewById(R.id.webview_linearlayout_root)
        mTitleTextView = findViewById(R.id.webview_textview_title)
        mBeginloadingTextView = findViewById(R.id.webview_textview_beginloading)
        mLoadingTextView = findViewById(R.id.webview_textview_loading)
        mEndLoadingTextView = findViewById(R.id.webview_textview_endloading)

        mWebView = WebView(applicationContext).apply {
            val webSettings = settings
            webSettings.javaScriptEnabled = true

            //设置自适应屏幕
            //将图片调整到适合webview大小
            webSettings.useWideViewPort = true
            //缩放至屏幕的大小
            webSettings.loadWithOverviewMode = true

            //缩放操作
            //支持缩放，默认为true，是下面的前提
            webSettings.setSupportZoom(true)
            //设置内置缩放的空间，若为false，则该webview不可以缩放
            webSettings.builtInZoomControls = true
            //隐藏原生的缩放控件
            webSettings.displayZoomControls = false

            //其它细节操作
            //关闭webview中缓存
            webSettings.cacheMode = LOAD_NO_CACHE
            //设置可访问文件
            webSettings.allowFileAccess = true
            //支持通过JS打开新窗口
            webSettings.javaScriptCanOpenWindowsAutomatically = true
            //支持自动加载图片
            webSettings.loadsImagesAutomatically = true
            //支持编码格式
            webSettings.defaultTextEncodingName = "utf-8"

            //加载apk中的url
//        loadUrl("file:///android_asset/web/transport.html")
            //加载一个网页
            loadUrl("https://www.baidu.com")
            //还支持加载手机本地的Html页面
//        loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");
            //加载HTML页面的一小段内容
//        loadData(String data, String mimeType, String encoding)

            //解决：loadUrl默认弹出选择浏览器访问问题，参考：https://blog.csdn.net/dszgf5717/article/details/47905869
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    Log.i(TAG, "shouldOverrideUrlLoading.view = $view,url = $url")
                    view.loadUrl(url)
                    return true
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    Log.i(TAG, "onPageStarted.view = $view,url = $url")
                    mBeginloadingTextView.text = "开始加载了"
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.i(TAG, "onPageFinished.view = $view,url = $url")
                    mEndLoadingTextView.text = "加载结束了"
                }

                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)
                    Log.i(TAG, "onLoadResource.view = $view,url = $url")
                }

                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    Log.i(
                        TAG,
                        "onReceivedError.view = $view,errorCode = $errorCode,description = $description,failingUrl=$failingUrl"
                    )
                }

                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    super.onReceivedHttpError(view, request, errorResponse)
                    Log.i(TAG, "onReceivedHttpError.view = $view,request = ${request.toString()},errorResponse=${errorResponse.toString()}")
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                    Log.i(TAG, "onReceivedError.view=$view,request=${request.toString()},error=${error.toString()}")
                }

                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    super.onReceivedSslError(view, handler, error)
                    Log.i(TAG, "onReceivedSslError.view=$view,handler=${handler.toString()},error=${error.toString()}")
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    Log.i(TAG, "onReceivedTitle.view = $view,title = $title")
                    mTitleTextView.text = title
                }

                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    Log.i(TAG, "onProgressChanged.view = $view,newProgress = $newProgress")
                    mLoadingTextView.text = "$newProgress%"
                }

                override fun onJsPrompt(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    defaultValue: String?,
                    result: JsPromptResult?
                ): Boolean {
                    return super.onJsPrompt(view, url, message, defaultValue, result)
                }
            }
        }

        //动态添加，避免内存泄露
        mRootLinearLayout.addView(mWebView)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //按Back键，返回上一页
        if ((keyCode === KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        //避免内存泄露
        mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        mWebView.clearHistory()

        (mWebView.parent as ViewGroup).removeView(mWebView)
        mWebView.destroy()

        super.onDestroy()
    }
}
