package com.android.xknowledge.optimize

import com.android.xknowledge.ListActivity
import com.android.xknowledge.optimize.leakcanary.LeakcanaryActivity
import com.android.xknowledge.optimize.strictmode.StrictActivity

class OptimizeActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("StrictActivity", "StrictMode相关", StrictActivity::class.java),
            ListItem("LeakcanaryActivity", "Leakcanary相关", LeakcanaryActivity::class.java)
        )
    }
}