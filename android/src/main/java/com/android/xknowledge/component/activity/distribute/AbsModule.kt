package com.android.xknowledge.component.activity.distribute

import android.os.Bundle

abstract class AbsModule {
    abstract fun init(moduleContext: ModuleContext, extend: String)

    abstract fun onSaveInstanceState(outState: Bundle)

    abstract fun onResume()

    abstract fun onPause()

    abstract fun onStop()

    abstract fun onOrientationChanges(isLandscape: Boolean)

    abstract fun onDestroy()
}
