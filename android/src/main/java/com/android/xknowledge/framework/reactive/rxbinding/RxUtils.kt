package com.android.xknowledge.framework.reactive.rxbinding

import io.reactivex.ObservableTransformer

//对象声明，参考：https://www.kotlincn.net/docs/reference/object-declarations.html
object RxUtils {
    /**
     * 对RxView绑定的事件
     * 封装了防止重复点击和RxLifecycle的生命周期
     */
    fun useRxViewTransformer(): ObservableTransformer<Any, Any> {
        return ObservableTransformer { upstream ->
            upstream.compose(RxJavaUtils.preventDuplicateClicksTransformer())
        }
    }
}
