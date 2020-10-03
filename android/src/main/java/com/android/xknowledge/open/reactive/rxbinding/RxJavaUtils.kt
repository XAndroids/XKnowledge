package com.android.xknowledge.open.reactive.rxbinding

import io.reactivex.ObservableTransformer
import java.util.concurrent.TimeUnit

object RxJavaUtils {
    /**
     * 防止重复点击的Transformer
     */
    fun <T> preventDuplicateClicksTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream -> upstream.throttleFirst(3000, TimeUnit.MILLISECONDS) }
    }
}