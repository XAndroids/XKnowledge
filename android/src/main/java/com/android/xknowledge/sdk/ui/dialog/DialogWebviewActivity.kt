package com.android.xknowledge.sdk.ui.dialog

import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class DialogWebviewActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("DialogWebviewActivity", "DialogWebviewActivity_onCreate")
        setContentView(R.layout.activity_dialog_webview)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.dialog_webview_constraintlayout)
        constraintLayout.setOnClickListener {
            val webviewDialog = WebviewDialog()
            webviewDialog.show(supportFragmentManager, "WebviewDialog")
        }
    }
}
