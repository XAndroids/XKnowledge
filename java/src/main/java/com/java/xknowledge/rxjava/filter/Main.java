package com.java.xknowledge.rxjava.filter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
//Filter（），参考：https://www.jianshu.com/p/c3a930a03855
public class Main {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 1. 发送5个事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }
        }).filter(new Predicate<Integer>() {
            // 2. 采用filter（）变换操作符

            // 根据test()的返回值 对被观察者发送的事件进行过滤 & 筛选
            // a. 返回true，则继续发送
            // b. 返回false，则不发送（即过滤）
            @Override
            public boolean test(Integer integer) throws Exception {
                // 本例子 = 过滤了整数≤3的事件
                return integer > 3;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("filter 开始采用Subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("filter 对Next事件作出响应" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("filter 对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("filter 对Complete事件作出响应");
            }
        });
    }
}
