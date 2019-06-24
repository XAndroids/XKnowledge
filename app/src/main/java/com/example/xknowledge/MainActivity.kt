package com.example.xknowledge

import com.example.xknowledge.hybrid.HybridActivity
import com.example.xknowledge.ui.UiActivity
import com.example.xknowledge.ui.sensor.SensorActivity

class MainActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("UI", "页面展示和交互相关", UiActivity::class.java),
            ListItem("Sensor", "传感器功能现相关", SensorActivity::class.java),
            ListItem("Hybrid", "Hybrid混合开发相关", HybridActivity::class.java)
        )
    }
}
