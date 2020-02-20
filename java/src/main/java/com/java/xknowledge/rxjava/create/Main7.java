package com.java.xknowledge.rxjava.create;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//interval,参考：https://www.jianshu.com/p/e19f8ed863b1
public class Main7 {
    public static void main(String[] args) {
        // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
        Observable.interval(3, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("interval onSubscribe");
            }

            @Override
            public void onNext(Long value) {
                System.out.println("interval onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("interval onError");
            }

            @Override
            public void onComplete() {
                System.out.println("interval onComplete");
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
