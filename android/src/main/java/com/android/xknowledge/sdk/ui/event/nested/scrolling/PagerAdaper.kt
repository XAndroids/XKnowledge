package com.android.xknowledge.sdk.ui.event.nested.scrolling

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdaper(fragmentManager: FragmentManager, val titleArray: Array<String>) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val simpleFragment = PagerFragment()
        simpleFragment.arguments = Bundle().apply {
            putString(PagerFragment.TITLE, titleArray[position])
        }
        return simpleFragment
    }

    override fun getCount(): Int = titleArray.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titleArray[position]
    }
}