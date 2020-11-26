package com.android.xknowledge.sdk.other

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_upload_exit.*

/**
 * Android 11上传进程退出原因
 * 参考：https://developer.android.com/about/versions/11/features?hl=zh-cn#app-process-exit-reasons
 */
class UploadExitActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_exit)
        val exitSender = ExitSender()
        exitreasons_textview_info.text = exitSender.getAndSendExit(this)
    }
}