package com.android.xknowledge.sdk.component.broadcast

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * 静态广播的使用
 * Android8.0显示静态广播，显示指定可以，参考：https://blog.csdn.net/u011386173/article/details/82889275
 */
class StaticBroadcastActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staticbroadcast)
        findViewById<Button>(R.id.staticbroadcast_button_send).setOnClickListener {
            //发送静态广播，Android8.0以上隐式发送无法接受，方法一改为显示发送广播
            //FIXME 为什么接受不到，也不打印静态广播日志？？
            val intent = Intent("com.byread.static")
            intent.component = ComponentName(
                "com.android.xknowledge",
                "com.android.xknowledge.sdk.component.broadcast.StaticBroadcastReceiver"
            )
            intent.putExtra("msg", "接收静态注册广播成功！")
            sendBroadcast(intent)
        }
    }
}
