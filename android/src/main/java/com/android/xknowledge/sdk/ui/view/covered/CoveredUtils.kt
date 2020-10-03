package com.android.xknowledge.sdk.ui.view.covered

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewGroup

class CoveredUtils {

    companion object {
        /**
         * View是否被遮挡了（不完全显示，如未完全在屏幕中被屏幕遮挡，被其它View遮挡等
         */
        fun isViewCovered(view: View): Boolean {
            var currentView = view

            //判断当前View是否完全展示在屏幕中，是否被"屏幕遮挡"一部分
            val currentViewRect = Rect()
            val partVisible = currentView.getGlobalVisibleRect(currentViewRect)
            val localRect = Rect()
            currentView.getLocalVisibleRect(localRect)
            val totalHeightVisible =
                (currentViewRect.bottom - currentViewRect.top) >= view.measuredHeight
            val totalWidthVisible =
                (currentViewRect.right - currentViewRect.left) >= view.measuredWidth
            val totalViewVisible = partVisible && totalHeightVisible && totalWidthVisible
            if (!totalViewVisible) {
                return true
            }

            //判断当前Views是否被兄弟或父视图遮挡
            while (currentView.parent is ViewGroup) {
                val currentParent = currentView.parent as ViewGroup
                Log.i("CoveredActivity","currentParent = ${currentParent.id}")

                //如果父View不可见，则在屏幕总不显示，被"完全遮挡"
                if (currentParent.visibility != View.VISIBLE) {
                    return true
                }

                val start = indexOfViewInParent(currentView, currentParent)
                for (i in (start + 1) until currentParent.childCount) {
                    val viewRect = Rect()
                    view.getGlobalVisibleRect(viewRect)

                    val otherView = currentParent.getChildAt(i)
                    Log.i("CoveredActivity","otherView = ${otherView.id}")
                    val otheViewRect = Rect()
                    otherView.getGlobalVisibleRect(otheViewRect)

                    //如果视图和任何一个兄弟视图有交集，则"完全或部分遮挡"
                    if (Rect.intersects(viewRect, otheViewRect)) {
                        return true
                    }
                }

                //向上层视图遍历，再次循环判断是否有父视图有交集
                currentView = currentParent
            }

            return false
        }

        private fun indexOfViewInParent(view: View, parent: ViewGroup): Int {
            var resultIndex = 0

            for (index in 0..parent.childCount) {
                if (parent.getChildAt(index) == view) {
                    resultIndex = index
                    break
                }
            }

            return resultIndex
        }
    }
}