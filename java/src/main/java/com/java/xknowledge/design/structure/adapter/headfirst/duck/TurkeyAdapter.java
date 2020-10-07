package com.java.xknowledge.design.structure.adapter.headfirst.duck;

import com.java.xknowledge.design.structure.adapter.headfirst.turkey.Turkey;

/**
 * 鸭子适配器，将火鸡类型适配呈鸭子类型
 */
public class TurkeyAdapter implements Duck {    //首先，需要实现你希望转换成的类型接口
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {    //接着，需要取得要适配的对象的引用，这里通过构造器获得
        this.turkey = turkey;
    }

    public void quack() {    //然后，需要实现接口中所有的方法
        turkey.gobble();
    }

    public void fly() {
        for (int i = 0; i < 5; i++) {    //火鸡飞行距离短，我们连续多飞几次
            turkey.fly();
        }
    }
}
