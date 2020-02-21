package com.java.xknowledge.rxjava.conversion;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Main3 {
    public static void main(String[] args) {
        //Buffer，设置缓存区大小 & 步长
        // 缓存区大小 = 每次从被观察者中获取的事件数量
        // 步长 = 每次获取新事件的数量
        Observable.just(1, 2, 3, 4, 5).buffer(3, 2).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(List<Integer> value) {
                System.out.println("缓冲区的数量 = " + value.size());
                for (int i = 0; i < value.size(); i++) {
                    System.out.println("缓冲区事件 = " + value.get(i));
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
