package com.android.xknowledge.ui.listener

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

class ScreamUtils {
    companion object {
        fun getRealHeight(context: Context): Int {
            val windowManager =
                context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)
            return displayMetrics.heightPixels - getStatusBarHeight(context)
        }

        private fun getStatusBarHeight(context: Context): Int {
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android");
            return context.resources.getDimensionPixelSize(resourceId);
        }
    }
}