package com.example.xknowledge.ui.view.dialog

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class DialogCheckedUtils {

    companion object {
        /**
         * 是否有对话框展示
         * 该检测方式没有办法检测AlertDialog的展示
         */
        fun hasDialogShow(activity: FragmentActivity): Boolean {
            for (fragment in activity.supportFragmentManager.fragments) {
                if (fragment is DialogFragment) {
                    return true
                }
            }
            return false
        }
    }
}