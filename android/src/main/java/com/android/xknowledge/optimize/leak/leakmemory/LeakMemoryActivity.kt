package com.android.xknowledge.optimize.leak.leakmemory

import com.android.xknowledge.ListActivity

class LeakMemoryActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("BroadcastReceiver", "广播未注销", BroadcastReceiverLeakActivity::class.java),
            ListItem("StaticReference", "静态引用", StaticReferenceLeakActivity::class.java),
            ListItem("Singleton", "单例类", SingletonLeakActivity::class.java),
            ListItem("InnerClassReference", "内存类引用", InnerClassReferenceLeakActivity::class.java),
            ListItem("AnonymousClassReference", "匿名类引用", AnonymousClassReferenceLeakActivity::class.java),
            ListItem("AsyncTaskReference", "AsyncTask引用", AsyncTaskReferenceLeakActivity::class.java),
            ListItem("ThreadReference", "Thread 引用", ThreadReferenceLeakActivity::class.java),
            ListItem("TimerTaskReference", "TimeTask 引用", TimerTaskReferenceLeakActivity::class.java),
            ListItem("HandlersReference", "Handler引用", HandlersReferenceLeakActivity::class.java)
        )
    }
}