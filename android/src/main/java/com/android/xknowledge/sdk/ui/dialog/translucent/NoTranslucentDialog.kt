package com.android.xknowledge.sdk.ui.dialog.translucent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams
import androidx.fragment.app.DialogFragment
import com.android.xknowledge.R

class NoTranslucentDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_translucent, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //实现没有蒙层，并且对话框可以点击的效果
        //参考：https://stackoverflow.com/questions/15382540/allow-outside-touch-for-dialogfragment/28043015
        dialog?.window?.let {
            it.setFlags(LayoutParams.FLAG_NOT_TOUCH_MODAL, LayoutParams.FLAG_NOT_TOUCH_MODAL)
            it.clearFlags(LayoutParams.FLAG_DIM_BEHIND)
        }
    }
}