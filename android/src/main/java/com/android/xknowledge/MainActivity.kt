package com.android.xknowledge

import com.android.xknowledge.aidl.AidlActivity
import com.android.xknowledge.component.ComponentActivity
import com.android.xknowledge.framework.FrameworkActivity
import com.android.xknowledge.hybrid.HybridActivity
import com.android.xknowledge.jetpack.JetpackActivity
import com.android.xknowledge.optimize.OptimizeActivity
import com.android.xknowledge.other.OtherActivity
import com.android.xknowledge.security.SecurityActivity
import com.android.xknowledge.test.TestActivity
import com.android.xknowledge.thread.ThreadActivity
import com.android.xknowledge.ui.UiActivity
import com.android.xknowledge.ui.sensor.SensorActivity
import com.reactnative.knowledge.ReactNativeActivity

class MainActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("UI", "页面展示和交互相关", UiActivity::class.java),
            ListItem("Compontent", "四大组件相关", ComponentActivity::class.java),
            ListItem("Thread", "线程相关", ThreadActivity::class.java),
            ListItem("Sensor", "传感器功能现相关", SensorActivity::class.java),
            ListItem("Framework", "开源框架相关", FrameworkActivity::class.java),
            ListItem("Jetpack", "Jetpack组件相关", JetpackActivity::class.java),
            ListItem("Hybrid", "Hybrid混合开发相关", HybridActivity::class.java),
            ListItem("Test", "自动化测试相关", TestActivity::class.java),
            ListItem("Optimize", "性能优化相关", OptimizeActivity::class.java),
            ListItem("Security", "安全相关", SecurityActivity::class.java),
            ListItem("ReactNative", "React Native相关", ReactNativeActivity::class.java),
            ListItem("Other", "其它相关", OtherActivity::class.java)
        )
    }
}
