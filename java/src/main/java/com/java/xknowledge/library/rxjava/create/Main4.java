package com.java.xknowledge.library.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//额外，参考：https://www.jianshu.com/p/e19f8ed863b1
public class Main4 {
    public static void main(String[] args) {
        //empty()该方法创建的被观察者对象发送事件的特点：有onSubscribem回调，
        //仅发送Complete事件，直接通知完成
        Observable.empty().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("empty onSubscribe");
            }

            @Override
            public void onNext(Object value) {
                System.out.println("empty onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("empty onError");
            }

            @Override
            public void onComplete() {
                System.out.println("empty onComplete");
            }
        });

        //error(0方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
        //但有onSubscribe回调，观察者接收后会直接调用onError（）
        Observable.error(new RuntimeException()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("error onSubscribe");
            }

            @Override
            public void onNext(Object value) {
                System.out.println("error onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error onError");
            }

            @Override
            public void onComplete() {
                System.out.println("error onComplete");
            }
        });

        //never()该方法创建的被观察者对象发送事件的特点：不发送任何事件
        //但有onSubscribe回调，观察者接收后什么都不调用
        Observable.never().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("never onSubscribe");
            }

            @Override
            public void onNext(Object value) {
                System.out.println("never onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("never onError");
            }

            @Override
            public void onComplete() {
                System.out.println("never onComplete");
            }
        });
    }
}
