package com.android.xknowledge.framework

import com.android.xknowledge.ListActivity
import com.android.xknowledge.framework.aspectj.AspectJActivity
import com.android.xknowledge.framework.inject.InjectActivity
import com.android.xknowledge.framework.hotfix.MultidexActivity
import com.android.xknowledge.framework.module.ModuleActivity
import com.android.xknowledge.framework.mvx.MVXActivity
import com.android.xknowledge.framework.mvx.mvn.MVNActivity
import com.android.xknowledge.framework.plugin.PluginActivity

class FrameworkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("AspectJ", "切面编程", AspectJActivity::class.java),
            ListItem("Multidex", "Multidex热修复", MultidexActivity::class.java),
            ListItem("Plugin", "Plugin插件化", PluginActivity::class.java),
            ListItem("Module", "Module组件化", ModuleActivity::class.java),
            ListItem("Inject", "运行时反射", InjectActivity::class.java),
            ListItem("MVX", "MVX模式演进", MVXActivity::class.java)
        )
    }
}