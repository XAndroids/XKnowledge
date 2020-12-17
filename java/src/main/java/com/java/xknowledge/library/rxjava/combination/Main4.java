package com.java.xknowledge.library.rxjava.combination;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

//Zip（），参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main4 {
    public static void main(String[] args) {
        // 创建第1个被观察者
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
            // 设置被观察者1在工作线程1中工作
        }).subscribeOn(Schedulers.io());

        //创建第2个被观察者
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                //尽管被观察者2的事件D没有事件与其合并，但还是会继续发送
                System.out.println("被观察者2发送了事件D");
                emitter.onNext("D");
                emitter.onComplete();
            }
            // 设置被观察者2在工作线程2中工作
        }).subscribeOn(Schedulers.newThread());
        // 假设不作线程控制，则该两个被观察者会在同一个线程中工作，即发送事件存在先后顺序，而不是同时发送

        //使用zip变换操作符进行事件合并
        // 注：创建BiFunction对象传入的第3个参数 = 合并后数据的数据类型
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("zip onSubscribe");
            }

            @Override
            public void onNext(String value) {
                System.out.println("zip onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("zip onError");
            }

            @Override
            public void onComplete() {
                System.out.println("zip onComplete");
            }
        });
    }
}
