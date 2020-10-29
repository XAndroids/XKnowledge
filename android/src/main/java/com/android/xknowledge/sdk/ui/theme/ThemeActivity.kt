package com.android.xknowledge.sdk.ui.theme

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * 深色主题实践
 * Theme.AppCompat.DayNight主题，系统切换使用night-quality资源目录下资源，使用没有设置颜色和?android:attr动态资源情况生效
 * Force Dark，系统分析视图，设置对应的深色颜色，适应硬编码系统资源@android:color和自定义资源@color情况自动适配，但颜色视觉效果没法保证；
 * values-night，自定义深色模式颜色，适用于Theme.AppCompat.DayNight和Force Dark都没有处理的情况
 * 参考：
 * 官方文档：https://developer.android.com/guide/topics/ui/look-and-feel/darktheme#%E6%9B%B4%E6%94%B9%E5%BA%94%E7%94%A8%E5%86%85%E4%B8%BB%E9%A2%98%E8%83%8C%E6%99%AF
 * 主题适配方案：https://blog.csdn.net/guolin_blog/article/details/106061657
 * forceDarkAllow原理：https://juejin.im/post/6844904002086240270
 * @android, ?attr/ 和 ?android 的区别：https://www.jianshu.com/p/4bdb77e034b8
 */
class ThemeActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        findViewById<Button>(R.id.theme_button_change).setOnClickListener {
            if (isDarkTheme(this)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun isDarkTheme(context: Context): Boolean {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }
}