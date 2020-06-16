package com.android.xknowledge.component.activity

import com.android.xknowledge.ListActivity
import com.android.xknowledge.component.activity.distribute.activity.DistributeActivity
import com.android.xknowledge.component.activity.lifecycler.LifeCycleActivity
import com.android.xknowledge.component.activity.task.TaskActivity

class ActivityActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("LifeCycle", "Activity的生命周期相关", LifeCycleActivity::class.java),
            ListItem("Distribute", "跨组件Activity分发相关", DistributeActivity::class.java),
            ListItem("Task", "ActivityTask栈相关", TaskActivity::class.java)
        )
    }
}