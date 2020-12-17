package com.java.xknowledge.library.rxjava.create;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//range，参考：https://www.jianshu.com/p/e19f8ed863b1
public class Main9 {
    public static void main(String[] args) {
        // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
        Observable.range(3, 10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("range onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("range onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("range onError");
            }

            @Override
            public void onComplete() {
                System.out.println("range onComplete");
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
