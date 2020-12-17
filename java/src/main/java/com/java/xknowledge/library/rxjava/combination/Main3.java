package com.java.xknowledge.library.rxjava.combination;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
//concatDelayError（） / mergeDelayError（）,参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main3 {
    public static void main(String[] args) {
        Observable.concat(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
                emitter.onError(new NullPointerException());
                emitter.onComplete();
            }
        }), Observable.just(4, 5, 6)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("concat onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("concat onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("concat onError");
            }

            @Override
            public void onComplete() {
                System.out.println("concat onComplete");
            }
        });


        Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                // 发送Error事件，因为使用了concatDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
                emitter.onError(new NullPointerException());
                emitter.onComplete();
            }
        }), Observable.just(4, 5, 6)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("concat onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("concat onNext = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("concat onError");
            }

            @Override
            public void onComplete() {
                System.out.println("concat onComplete");
            }
        });
    }
}
