package com.java.xknowledge.rxjava.function;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
//do（），参考：https://www.jianshu.com/p/b0c3669affdb
public class Main3 {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable("发生错误了"));
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {
            // 1. 当Observable每发送1次数据事件就会调用1次
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                System.out.println("doOnEach : " + integerNotification.getValue());
            }
        }).doOnNext(new Consumer<Integer>() {
            // 2. 执行Next事件前调用
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("doOnNext: " + integer);
            }
        }).doAfterNext(new Consumer<Integer>() {
            // 3. 执行Next事件后调用
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("doAfterNext: " + integer);
            }
        }).doOnComplete((new Action() {
            // 4. Observable正常发送事件完毕后调用
            @Override
            public void run() throws Exception {
                System.out.println("doOnComplete:");
            }
        })).doOnError(new Consumer<Throwable>() {
            // 5. Observable发送错误事件时调用
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("doOnError:");
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            // 6. 观察者订阅时调用
            @Override
            public void accept(Disposable disposable) throws Exception {
                System.out.println("doOnSubscribe:");
            }
        }).doAfterTerminate(new Action() {
            // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
            @Override
            public void run() throws Exception {
                System.out.println("doAfterTerminate:");
            }
        }).doFinally(new Action() {
            // 8. 最后执行
            @Override
            public void run() throws Exception {
                System.out.println("doFinally:");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

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
