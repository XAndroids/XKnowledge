package com.java.xknowledge.library.rxjava.combination;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//startWith（） / startWithArray（）,参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main8 {
    public static void main(String[] args) {
        //在一个被观察者发送事件前，追加发送一些数据
        //注：追加数据顺序 = 后调用先追加
        Observable.just(4, 5, 6).startWith(0)// 追加单个数据 = startWith()
                .startWithArray(1, 2, 3)// 追加多个数据 = startWithArray()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("本次发送的数据是 = " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("对onError事件做出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("对onComplete事件做出响应");
                    }
                });


        Observable.just(4, 5, 6)
                .startWith(Observable.just(1, 2, 3))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("本次发送的数据是 = " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("对onError事件做出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("对onComplete事件做出响应");
                    }
                });

    }
}
