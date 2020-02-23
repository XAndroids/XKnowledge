package com.java.xknowledge.rxjava.function;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class Main9 {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("发生错误了"));
                emitter.onNext(3);
            }

        }).retry(new Predicate<Throwable>() {
            // 拦截错误后，判断是否需要重新发送请求
            @Override
            public boolean test(Throwable throwable) throws Exception {
                System.out.println("retry错误: " + throwable.toString());
                //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                return false;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("对Next事件 = " + value + " 作出响应");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("对Complete事件作出响应");
            }
        });
    }
}
