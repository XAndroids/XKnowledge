package com.android.xknowledge.ui.view.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.google.android.material.tabs.TabLayout

class ViewPagerCacheActivity : TitleActivity() {
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ViewPagerCacheActivity", "ViewPagerCacheActivity_onCreate")
        setContentView(R.layout.activity_viewpager_cache)

        mTabLayout = findViewById(R.id.viewpagercache_tablelayout_parent)
        mViewPager = findViewById(R.id.viewpagercache_viewpager_parent)

        mViewPager.adapter = FragmentAdapter(supportFragmentManager, CacheFragmentManager.getFragmentList(), CacheFragmentManager.mTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
        //一次性创建Fragment的大小，如果limit大返回原来页面不会重新回调生命周期！！！
//        mViewPager.offscreenPageLimit = CacheFragmentManager.mTitleList.size
    }

    class FragmentAdapter(
        fragmentManager: FragmentManager,
        private val mFragmentList: MutableList<Fragment>,
        private val mTitleList: List<String>
    ) :
        FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment {
            Log.i("ViewPagerCacheActivity", "FragmentAdapter_getItem: $position")
            //FIXME 无论自己缓存还是重新创建，Adapter都不会重新创建Fragment
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            Log.i("ViewPagerCacheActivity", "FragmentAdapter_getCount: mTitleList.size")
            return mTitleList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            Log.i("ViewPagerCacheActivity", "FragmentAdapter_getPageTitle: $position")
            return mTitleList[position]
        }
    }
}
