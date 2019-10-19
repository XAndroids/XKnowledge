package com.android.xknowledge.component.activity.distribute

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import java.util.logging.Handler

abstract class BasicModule : AbsModule() {
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
