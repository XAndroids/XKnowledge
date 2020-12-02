package com.android.xknowledge.framework.inject

/**
 * Kotlin注解声明
 * https://www.kotlincn.net/docs/reference/annotations.html
 * FIXME 为什么Kotlin重写一遍呢？？AnnotationTarget、AnnotationRetention等类
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class InjectView(val value: Int)