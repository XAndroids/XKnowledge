package com.android.xknowledge.optimize

import com.android.xknowledge.ListActivity
import com.android.xknowledge.optimize.blockcanary.BlockCanaryActivity
import com.android.xknowledge.optimize.gc.GcActivity
import com.android.xknowledge.optimize.leakcanary.LeakcanaryActivity
import com.android.xknowledge.optimize.leakmemory.LeakMemoryActivity
import com.android.xknowledge.optimize.strictmode.StrictActivity
import com.android.xknowledge.optimize.systrace.SystraceActivity

class OptimizeActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("StrictActivity", "StrictMode相关", StrictActivity::class.java),
            ListItem("LeakcanaryActivity", "Leakcanary相关", LeakcanaryActivity::class.java),
            ListItem("GcActivity", "GC日志", GcActivity::class.java),
            ListItem("LeakMemoryActivity", "内存泄露相关", LeakMemoryActivity::class.java),
            ListItem("SystraceActivity", "Systrace相关", SystraceActivity::class.java),
            ListItem("BlockCanaryActivity", "BlockCanary相关", BlockCanaryActivity::class.java)
        )
    }
}