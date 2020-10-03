package com.android.xknowledge.sdk.ui.dialog.translucent

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.xknowledge.R

class TranslucentDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            builder.setView(inflater.inflate(R.layout.dialog_translucent, null))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

        dialog.let {
            //取消点击，点击外部和Back按键关闭对话框
            //参考：https://www.jianshu.com/p/b6b89eda6904
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
//            it.setOnKeyListener { _, keyCode, _ -> keyCode == KeyEvent.KEYCODE_BACK }
        }

        return dialog
    }
}
