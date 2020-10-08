package com.java.xknowledge.design.structure.decorator.enjoy;

import com.java.xknowledge.design.common.bag.AppleBag;
import com.java.xknowledge.design.common.bag.Bag;

/**
 * 装饰器模式
 * 参考《享学设计模式-装饰者模式》
 */
public class DecoratorClient {
    public static void main(String[] args){
        sendFruit();
    }

    public static void sendFruit(){
        //现需要增加防伪标识
        Bag bag = new CheckedBagDecorator(new AppleBag());//防伪功能
        bag = new ReinforceBagDecorator(bag);//加固功能
        bag = new SpeedDecorator(bag);//加急功能

        bag.pack();
    }
}
