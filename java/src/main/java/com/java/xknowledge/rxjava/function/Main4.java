package com.java.xknowledge.rxjava.function;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
// onErrorReturn，参考：https://www.jianshu.com/p/b0c3669affdb
public class Main4 {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("发生错误了"));
                emitter.onNext(3);
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                // 捕捉错误异常
                System.out.println("在onErrorReturn处理了错误: " + throwable.toString());
                return 6666;
                // 发生错误事件后，发送一个"666"事件，最终正常结束
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                System.out.println("接收到了事件 = " + value);
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
