package com.example.xknowledge.ui.view.dialog

import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

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
