package com.java.xknowledge.rxjava.create;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//intervalRange，参考：https://www.jianshu.com/p/e19f8ed863b1
public class Main8 {
    public static void main(String[] args) {
        // 该例子发送的事件序列特点：
        // 1. 从3开始，一共发送10个事件；
        // 2. 第1次延迟2s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("intervalRange onSubscribe");
            }

            @Override
            public void onNext(Long value) {
                System.out.println("intervalRange onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("intervalRange onError");
            }

            @Override
            public void onComplete() {
                System.out.println("intervalRange onComplete");
            }
        });

        //执行输入，避免退出main线程
        try {
            char ch = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
