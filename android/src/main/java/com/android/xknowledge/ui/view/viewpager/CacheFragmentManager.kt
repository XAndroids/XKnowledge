package com.android.xknowledge.ui.view.viewpager

import androidx.fragment.app.Fragment

class CacheFragmentManager {

    companion object {
        internal var mFragmentList = mutableListOf<Fragment>()
        internal var mTitleList = listOf("Fragment1", "Fragment2", "Fragment3", "Fragment4", "Fragment5")

        fun getFragmentList(): MutableList<Fragment> {
            if (mFragmentList.size == 0) {
                for (title in mTitleList) {
                    mFragmentList.add(CacheFragment.newInstance(title))
                }
            }

            return mFragmentList
        }
    }
}