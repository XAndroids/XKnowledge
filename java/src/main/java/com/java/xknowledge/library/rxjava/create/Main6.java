package com.java.xknowledge.library.rxjava.create;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//timer（），参考：https://www.jianshu.com/p/e19f8ed863b1
public class Main6 {
    public static void main(String[] args) {
        //timer, 延迟2s后，发送一个long类型数值
        Observable.timer(10, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("timer onSubscribe");
            }

            @Override
            public void onNext(Long value) {
                System.out.println("timer onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("timer onError");
            }

            @Override
            public void onComplete() {
                System.out.println("timer onComplete");
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
