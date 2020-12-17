package com.java.xknowledge.library.rxjava.disposble;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
//参考《RxJava 2.x》实战，Disposble章节
public class Main {
    public static void main(String[] args) {
        //Disposable是观察者和订阅者之间的纽带，可以用来取消订阅
        final Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //isDisposed()判断是否已经取消订阅，dispose()取消订阅
                if (!disposable.isDisposed()) {
                    System.out.println("disposable 取消订阅");
                    disposable.dispose();
                }
            }
        }, 3000);

        //执行输入，避免退出main线程
        try {
            char ch = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
