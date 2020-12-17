package com.java.xknowledge.library.rxjava.combination;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//concat（） / concatArray（），参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main {
    public static void main(String[] args) {
        // 1.concat（）：组合多个被观察者（≤4个）一起发送数据
        // 注：串行执行
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6), Observable.just(7, 8, 9),
                Observable.just(10, 11, 12)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("contact onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("contact 接受到了事件 = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("contact onError");
            }

            @Override
            public void onComplete() {
                System.out.println("contact onComplete");
            }
        });

        // 2.concatArray（）：组合多个被观察者一起发送数据（可＞4个）
        // 注：串行执行
        Observable.concatArray(Observable.just(1, 2, 3), Observable.just(4, 5, 6), Observable.just(7, 8, 9),
                Observable.just(10, 11, 12)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("concatArray onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("concatArray 接受到了事件 = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("concatArray onError");
            }

            @Override
            public void onComplete() {
                System.out.println("concatArray onComplete");
            }
        });
    }
}
