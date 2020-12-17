package com.java.xknowledge.library.rxjava.filter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//throttleFirst（）/ throttleLast（），参考：https://www.jianshu.com/p/c3a930a03855
public class Main6 {
    public static void main(String[] args) {
        //在某段时间内，只发送该段时间内第1次事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(500);
                emitter.onNext(3);
                Thread.sleep(500);
                emitter.onNext(4);
                Thread.sleep(500);
                emitter.onNext(5);
            }
        }).throttleFirst(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("throttleFirst 开始采用Subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("throttleFirst 对Next事件作出响应" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("throttleFirst 对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("throttleFirst 对Complete事件作出响应");
                    }
                });

        //在某段时间内，只发送该段时间内最后1次事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 隔段事件发送时间
                emitter.onNext(1);
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(500);
                emitter.onNext(3);
                Thread.sleep(500);
                emitter.onNext(4);
                Thread.sleep(500);
                emitter.onNext(5);
            }
        }).throttleLast(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("throttleLast 开始采用Subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("throttleLast 对Next事件作出响应" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("throttleLast 对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("throttleLast 对Complete事件作出响应");
                    }
                });

    }
}
