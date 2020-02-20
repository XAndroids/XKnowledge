package com.java.xknowledge.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//just操作符，参考：https://www.jianshu.com/p/e19f8ed863b1
//直接发送 传入的事件，最多只能发送10个参数
public class Main1 {
    public static void main(String[] args) {
        //1.通过just()操作符创建被观察者对象
        Observable.just(1, 2, 3, 4).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("observer 开始采用Subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("observer 对Next事件作出响应" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("observer 对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("observer 对Complete事件作出响应");
            }
        });
    }
}
