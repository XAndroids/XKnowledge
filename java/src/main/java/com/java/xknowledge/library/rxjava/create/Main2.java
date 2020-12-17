package com.java.xknowledge.library.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//fromArray()，参考：https://www.jianshu.com/p/e19f8ed863b1
//发送10个以上事件（数组形式）
public class Main2 {
    public static void main(String[] args) {
        // 1. 设置需要传入的数组
        Integer[] items = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        // 2. 创建被观察者对象（Observable）时传入数组
        Observable.fromArray(items).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("数组遍历");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("数组中的元素 = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("遍历结束");
            }
        });
    }
}
