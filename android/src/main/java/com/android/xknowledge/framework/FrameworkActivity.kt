package com.android.xknowledge.framework

import com.android.xknowledge.ListActivity
import com.android.xknowledge.framework.aspectj.AspectJActivity
import com.android.xknowledge.framework.eventbus.EventbusActivity
import com.android.xknowledge.framework.fresco.FrescoActivity
import com.android.xknowledge.framework.hotfix.MultidexActivity
import com.android.xknowledge.framework.plugin.PluginActivity
import com.android.xknowledge.framework.reactive.ReactiveActivity

class FrameworkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("EventBus", "EventBus使用", EventbusActivity::class.java),
            ListItem("Fresco", "Fresco使用", FrescoActivity::class.java),
            ListItem("Reactive", "响应式编程", ReactiveActivity::class.java),
            ListItem("AspectJ", "切面编程", AspectJActivity::class.java),
            ListItem("Multidex", "Multidex热修复", MultidexActivity::class.java),
            ListItem("Plugin", "Plugin插件化", PluginActivity::class.java)
        )
    }
}