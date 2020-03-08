package com.java.xknowledge.rxjava.disposble;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//参考《Rxjava 2.x 实战》,Disposble章节
public class Main2 {
    public static void main(String[] args) {
        //创建两个Observable，分贝返回Disposable
        Disposable disposable1 = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("Observable 1 :" + aLong);
                    }
                });
        Disposable disposable2 = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("Observable 2 :" + aLong);
                    }
                });

        //复合订阅容器，用来管理多个订阅，compositeDisposable.add()方法将disposable添加到容器中
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable1);
        compositeDisposable.add(disposable2);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //isDisposed()判断是否已经取消订阅，clear()切断所有事件
                if (!compositeDisposable.isDisposed()) {
                    System.out.println("compositeDisposable 取消订阅所有订阅");
                    compositeDisposable.clear();
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
