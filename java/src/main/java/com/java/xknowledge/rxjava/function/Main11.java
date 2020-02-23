package com.java.xknowledge.rxjava.function;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//repeat（）,参考：https://www.jianshu.com/p/b0c3669affdb
public class Main11 {
    public static void main(String[] args) {
        // 重复创建次数 =- 3次
        Observable.just(1, 2, 3).repeat(3).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("对Next事件 = " + value + " 作出响应");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("对Complete事件作出响应");
            }
        });
    }
}
