package com.java.xknowledge.library.rxjava.conversion;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

//FlatMap，参考：https://www.jianshu.com/p/904c14d253ba
public class Main1 {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onNext(6);
                emitter.onNext(7);
                emitter.onNext(8);
                emitter.onNext(9);
                emitter.onNext(10);
                emitter.onNext(11);
                emitter.onNext(12);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                //1.采用flatMap（）变换操作符
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件" + integer + "flatMap，拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }

                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //新合并生成的事件序列顺序是无序的，即 与旧序列发送事件的顺序无关
                //FIXME 运行多次顺序都一致
                System.out.println(s);
            }
        });
    }
}
