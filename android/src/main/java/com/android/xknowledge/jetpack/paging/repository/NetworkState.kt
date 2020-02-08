package com.android.xknowledge.jetpack.paging.repository

//枚举类，实现类型安全的枚举
//参考：枚举类，https://www.kotlincn.net/docs/reference/enum-classes.html
enum class Status {
    RUNNING, SUCCESS, FAILED
}

//message参数有默认值null，所以当LOADED构造时省略这个参数，使用默认值
//参考：默认参数，https://www.kotlincn.net/docs/reference/functions.html
data class NetworkState private constructor(val status: Status, val message: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)

        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}
