package com.android.xknowledge.ui.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.PopupWindow
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import androidx.appcompat.app.AlertDialog
import java.util.*


class DialogCheckActivity : TitleActivity() {
    lateinit var popupWindow: PopupWindow
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("DialogCheckActivity", "DialogCheckActivity_onCreate")
        setContentView(R.layout.activity_dialogcheck)

        timer = Timer()
        timer.schedule(CheckedTimerTask(), 0, 1000)

        //显示FragmentDialog
        findViewById<Button>(R.id.dialogchecked_fragment_button).setOnClickListener {
            CheckedDialogFragment().show(supportFragmentManager, "CheckedDialogFragment")
//            WebviewDialog().show(supportFragmentManager, "WebviewDialog")
        }

        //显示AlertDialog
        findViewById<Button>(R.id.dialogchecked_alert_button).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("CheckedAlertDialog")
            builder.create().show()
        }

        popupWindow = PopupWindow(this)
        popupWindow.contentView =
            LayoutInflater.from(this).inflate(R.layout.popupwindow_dialogchecked, null)

        val popupButton = findViewById<Button>(R.id.dialogchecked_popup_button)
        popupButton.setOnClickListener {
            popupWindow.showAsDropDown(popupButton)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("DialogCheckActivity", "DialogCheckActivity_onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("DialogCheckActivity", "DialogCheckActivity_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("DialogCheckActivity", "DialogCheckActivity_onPause")
        if (popupWindow.isShowing) {
            popupWindow.dismiss()
        }

        timer.cancel()
    }

    override fun onStop() {
        super.onStop()
        Log.i("DialogCheckActivity", "DialogCheckActivity_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("DialogCheckActivity", "DialogCheckActivity_onDestroy")
    }


    inner class CheckedTimerTask : TimerTask() {
        override fun run() {
            //hasWindowFocus检测方式，无法检测到PopupWindows的遮挡；
            Log.i(
                "DialogCheckActivity",
                "hasDialogShow = ${DialogCheckedUtils.hasDialogShow(this@DialogCheckActivity)},hasWindowFouse = ${this@DialogCheckActivity.hasWindowFocus()}"
            )
        }
    }
}
