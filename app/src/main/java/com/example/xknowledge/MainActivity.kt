package com.example.xknowledge

import com.example.xknowledge.component.ComponentActivity
import com.example.xknowledge.hybrid.HybridActivity
import com.example.xknowledge.test.TestActivity
import com.example.xknowledge.ui.UiActivity
import com.example.xknowledge.ui.sensor.SensorActivity

class MainActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("UI", "页面展示和交互相关", UiActivity::class.java),
            ListItem("Compontent", "四大组件相关", ComponentActivity::class.java),
            ListItem("Sensor", "传感器功能现相关", SensorActivity::class.java),
            ListItem("Hybrid", "Hybrid混合开发相关", HybridActivity::class.java),
            ListItem("Test", "自动化测试相关", TestActivity::class.java)
        )
    }
}
