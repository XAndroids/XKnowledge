package com.java.xknowledge.rxjava.induction;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//基于事件流的链式调用，参考：https://www.jianshu.com/p/a406b94f3188
public class Main2 {
    public static void main(String[] args) {
        // RxJava的链式操作
        // 1. 创建被观察者 & 生产事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("observer 开始采用Subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("observer 对Next事件作出响应" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("observer 对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("observer 对Complete事件作出响应");
            }
        });
    }
}
