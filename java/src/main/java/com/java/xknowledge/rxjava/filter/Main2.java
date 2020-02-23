package com.java.xknowledge.rxjava.filter;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

//ofType（）,参考：https://www.jianshu.com/p/c3a930a03855
public class Main2 {
    public static void main(String[] args) {
        Observable.just(1, "Carson", 3, "Ho", 5).ofType(Integer.class) // 筛选出 整型数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("获取到的整型事件元素是： " + integer);
                    }
                });
    }
}
