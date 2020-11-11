package com.android.xknowledge.sdk.ui.setting

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Android 10 Settings.Panel 设置面板的使用
 * 和原有设置页面的对应关系：
 * ACTION_INTERNET_CONNECTIVITY-ACTION_WIRELESS_SETTINGS
 * ACTION_WIFI-ACTION_WIFI_SETTINGS
 * ACTION_VOLUME-ACTION_SOUND_SETTINGS
 * ACTION_NFC-ACTION_NFC_SETTINGS
 * 在国内厂商系统上，两个跳转的页面略有差别
 * 参考：https://developer.android.com/about/versions/10/features#settings-panels
 */
class SettingActivity : TitleActivity() {
    lateinit var action: String
    lateinit var action2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //如果lambda表达式的参数未使用，那么可以用下划线取代其名称
            //参考：https://www.kotlincn.net/docs/reference/lambdas.html
            setting_radio_panel.setOnCheckedChangeListener { _, checkedId ->
                //when语句：https://www.kotlincn.net/docs/reference/control-flow.html
                when (checkedId) {
                    R.id.setting_radio_internet -> action =
                        Settings.Panel.ACTION_INTERNET_CONNECTIVITY
                    R.id.setting_radio_nfc -> action = Settings.Panel.ACTION_NFC
                    R.id.setting_radio_volume -> action = Settings.Panel.ACTION_VOLUME
                    R.id.setting_radio_wifi -> action = Settings.Panel.ACTION_WIFI
                }
            }
            setting_button_panel.setOnClickListener {
                startActivity(Intent(action));
            }
        }

        setting_radio_setting.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.setting_radio_wifiadd -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    action2 = Settings.ACTION_WIFI_ADD_NETWORKS
                }
                R.id.setting_radio_wifiip -> action2 = Settings.ACTION_WIFI_IP_SETTINGS
                R.id.setting_radio_wifi2 -> action2 = Settings.ACTION_WIFI_SETTINGS
                R.id.setting_radio_wireless -> action2 = Settings.ACTION_WIRELESS_SETTINGS
                R.id.setting_radio_nfc2 -> action2 = Settings.ACTION_NFC_SETTINGS
                R.id.setting_radio_sound -> action2 = Settings.ACTION_SOUND_SETTINGS
            }
        }

        setting_button_setting.setOnClickListener {
            startActivity(Intent(action2));
        }
    }
}