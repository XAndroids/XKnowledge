package com.java.xknowledge.design.action.template.enjoy.shoppingcart;

import com.java.xknowledge.design.common.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车模板抽象类
 * 提交订单逻辑框架设计完成（计算总金额/支付金额/送货上门），不确定支付金额延迟给子类实现
 */
public abstract class ShoppingCart {
    private List<Fruit> fruitList = new ArrayList<>();

    public void addFruit(Fruit fruit) {
        fruitList.add(fruit);
    }

    /**
     * 提交订单逻辑框架设计完成（计算总金额/支付金额/送货上门），不确定支付金额延迟给子类实现
     */
    public void submitOrder() {
        int money = balance();    //计算总金额
        pay(money);    //支付金额
        sendtoHome();    //送货上门
    }

    protected abstract void pay(int money);    //不确定支付延迟到具体子类实现

    private int balance() {
        int money = 0;
        for (Fruit fruit : fruitList) {
            money += fruit.price();
        }
        return money;
    }

    private void sendtoHome() {
        System.out.println("3公里送货上门");
    }
}
