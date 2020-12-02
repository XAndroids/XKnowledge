package com.android.xknowledge.framework.inject

/**
 * Kotlin注解声明
 * https://www.kotlincn.net/docs/reference/annotations.html
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class InjectView(val value: Int)