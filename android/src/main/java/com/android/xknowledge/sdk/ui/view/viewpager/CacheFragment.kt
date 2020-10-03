package com.android.xknowledge.sdk.ui.view.viewpager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.xknowledge.R

class CacheFragment : Fragment() {
    private var mTitle: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("ViewPagerCacheActivity", "CacheFragment_onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.takeIf {
            it.containsKey(TITLE)
        }?.apply {
            mTitle = getString(TITLE)?.toString()
            Log.i("ViewPagerCacheActivity", "CacheFragment_onCreate:$mTitle")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onCreateView:$mTitle")
        return inflater.inflate(R.layout.fragment_cache, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onViewCreated:$mTitle")
        val titleTextView = view.findViewById<TextView>(R.id.cache_textview)
        titleTextView.text = mTitle
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onActivityCreated:$mTitle")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onStart:$mTitle")
        super.onStart()
    }

    override fun onResume() {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onResume:$mTitle")
        super.onResume()
    }

    override fun onPause() {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onPause:$mTitle")
        super.onPause()
    }

    override fun onStop() {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onStop:$mTitle")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onDestroyView:$mTitle")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("ViewPagerCacheActivity", "CacheFragment_onDetach")
        super.onDetach()
    }

    companion object {
        internal const val TITLE = "title"

        fun newInstance(title: String): CacheFragment {
            Log.i("ViewPagerCacheActivity", "CacheFragment_newInstance: $title")
            val cacheFragment = CacheFragment()
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            cacheFragment.arguments = bundle
            return cacheFragment
        }
    }
}