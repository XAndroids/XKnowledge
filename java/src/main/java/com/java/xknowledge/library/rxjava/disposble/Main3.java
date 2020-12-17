package com.java.xknowledge.library.rxjava.disposble;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
//参考《RxJava 2.x实战》，Disposble章节
public class Main3 {
    public static void main(String[] args) {
        //compose操作符能够从数据流中得到原始的被观察者
        //当创建观察者的时候，compose操作符会立即执行，而不是像其它操作符在onNext()调用之后才能执行
        Observable.just(123, 456).compose(transformer()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String string) throws Exception {
                System.out.println("string = " + string);
            }
        });
    }

    //ObservableTransformer就是转换器的意思，将一个Observable转换成另一个Observable，和调用操作符一样
    private static ObservableTransformer<Integer, String> transformer() {
        return new ObservableTransformer<Integer, String>() {
            @Override
            public ObservableSource<String> apply(Observable<Integer> upstream) {
                //获取upstream数据流，进行转换操作
                return upstream.map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return String.valueOf(integer);
                    }
                });
            }
        };
    }
}
