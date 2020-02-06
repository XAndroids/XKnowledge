package com.android.xknowledge.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onStart() {
        super.onStart()
        //FIXME 没有实现预期的目标，虽然边框是透明的，但是也无法点操作下面的控件
        dialog.window?.setDimAmount(0f)
    }
}