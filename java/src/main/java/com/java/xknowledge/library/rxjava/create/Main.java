package com.java.xknowledge.library.rxjava.create;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//create（）操作符，参考：https://www.jianshu.com/p/e19f8ed863b1
public class Main {
    public static void main(String[] args) {
        // 1. 通过create（）操作符创建被观察者对象
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
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
