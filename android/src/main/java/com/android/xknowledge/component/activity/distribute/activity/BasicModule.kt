package com.android.xknowledge.component.activity.distribute.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import java.util.logging.Handler

/**
 * 模块基类，实现了需要分发的方法，包含共同包含的成员
 */
abstract class BasicModule :
    ModuleInterface {
    var mContext: FragmentActivity? = null
    var moduleContext: ModuleContext? = null
    private val handler: Handler? = null

    override fun onSaveInstanceState(outState: Bundle) {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {

    }

    override fun onOrientationChanges(isLandscape: Boolean) {

    }

    override fun onDestroy() {

    }
}
