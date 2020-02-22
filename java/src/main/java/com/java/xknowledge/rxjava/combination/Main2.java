package com.java.xknowledge.rxjava.combination;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Main2 {
    public static void main(String[] args) {
        //FIXME 为什么只调用merge onSubscribe？？？
        Observable.merge(Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("merge onSubscribe");
                    }

                    @Override
                    public void onNext(Long value) {
                        System.out.println("merge onNext = " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("merge onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("merge onComplete");
                    }
                });
    }
}
