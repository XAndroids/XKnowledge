package com.java.xknowledge.rxjava.filter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//throttleWithTimeout （） / debounce（），参考：https://www.jianshu.com/p/c3a930a03855
public class Main7 {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 隔段事件发送时间
                emitter.onNext(1);// 1和2之间的间隔小于指定时间1s，所以前1次数据（1）会被抛弃，2会被保留
                Thread.sleep(500);
                emitter.onNext(2);// 因为2和3之间的间隔大于指定时间1s，所以之前被保留的2事件将发出
                Thread.sleep(1500);
                emitter.onNext(3);
                Thread.sleep(500);
                emitter.onNext(4);
                Thread.sleep(500);
                emitter.onNext(5);
                Thread.sleep(1500);
                emitter.onNext(6);
            }
        }).throttleWithTimeout(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("throttleWithTimeout 开始采用Subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("throttleWithTimeout 对Next事件作出响应" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("throttleWithTimeout 对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("throttleWithTimeout 对Complete事件作出响应");
                    }
                });
    }
}
