package com.android.xknowledge.component.activity.distribute

object ModuleUtil {

    fun empty(c: Map<*, *>?): Boolean {
        return c == null || c.isEmpty()
    }

    fun empty(s: String?): Boolean {
        return s == null || s.isEmpty()
    }
}
