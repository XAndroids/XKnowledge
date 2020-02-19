package com.java.xknowledge.rxjava.induction;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

//分步骤实现、被观察者 Observable的subscribe()具备多个重载的方法
//参考：https://www.jianshu.com/p/a406b94f3188
public class Main3 {
    public static void main(String[] args) {
        //RxJava 提供了其他方法用于 创建被观察者对象Observable
        //方法1：just(T...)：直接将传入的参数依次发送出来
        Observable<String> observable = Observable.just("A", "B", "C");
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Consumer 对Next事件作出响应" + s);
            }
        });

        // 方法2：from(T[]) / from(Iterable<? extends T>) : 将传入的数组 / Iterable 拆分成具体对象后，依次发送出来
        String[] works = {"A", "B", "C"};
        final Observable<String> observable1 = Observable.fromArray(works);
        observable1.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Consumer2 对Next事件作出响应" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Consumer2 对Error事件作出响应");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Consumer2 对Complete事件作出响应");
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                System.out.println("Consumer2 开始采用Subscribe连接");
            }
        });
    }
}
