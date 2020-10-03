package com.android.xknowledge.sdk.ui.view.toast

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 *Toast位置控制：
 *  默认：
 *      mGravity：R.integer.config_toastDefaultGravity=Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
 *      mY：R.dimen.toast_y_offset=64dip
 *  mGravity：
 *      位置重心：TOP/BOTTOM/LEFT/RIGHT/CENTER_HORIZONTAL….
 *  mX/mY：
 *      相对于位置重心x轴/y轴偏移（像素）；
 *  mHorizontalMargin/mVerticalMargin：
 *      相对于位置重心x/y轴偏移（宽高百分比0f~1f）；
 */
class ToastActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
        findViewById<View>(R.id.toast_button_show).setOnClickListener {
            val toast =
                Toast.makeText(this@ToastActivity, "aaaaaaaaaaaaaaa", Toast.LENGTH_SHORT)
            toast.setMargin(0f, 1f);
            toast.setGravity(Gravity.TOP or Gravity.LEFT, 10, 0)
            toast.show()
            Log.i(
                "MainActivity", "mX = " + toast.xOffset + ",mY = " + toast.yOffset
                        + "mGravity = " + toast.gravity + ",mHorizontalMargin = " + toast.horizontalMargin
                        + ",mVerticalMargin = " + toast.verticalMargin
            )
        }
    }
}
