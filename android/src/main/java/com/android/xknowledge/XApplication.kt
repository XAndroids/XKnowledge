package com.android.xknowledge

import android.app.Activity
import android.app.Application


class XApplication : Application() {
    companion object {
        var sLeakyActivities = ArrayList<Activity>()
    }
}