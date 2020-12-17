package com.java.xknowledge.library.rxjava.filter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//take（）/takeLast()，参考：https://www.jianshu.com/p/c3a930a03855
public class Main5 {
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
        }).take(2) // 采用take（）变换操作符 指定了观察者只能接收2个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("take 开始采用Subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("take 对Next事件作出响应" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("take 对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("take 对Complete事件作出响应");
                    }
                });

        //FIxME 只有使用just操作符生效，create操作符无效？？
        Observable.just(1, 2, 3, 4, 5).takeLast(3) ////指定观察者只能接受被观察者发送的3个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("takeLast 开始采用Subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("takeLast 对Next事件作出响应" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("takeLast 对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("takeLast 对Complete事件作出响应");
                    }
                });
    }
}
