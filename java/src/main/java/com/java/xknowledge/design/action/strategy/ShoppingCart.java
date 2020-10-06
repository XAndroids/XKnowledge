package com.java.xknowledge.design.action.strategy;

import com.java.xknowledge.design.action.strategy.discount.Discount;
import com.java.xknowledge.design.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车，保存购物清单，执行优惠策略信息
 */
class ShoppingCart {
    private List<Fruit> fruitList = new ArrayList<>();
    private Discount discount;

    public void addFruit(Fruit fruit) {
        fruitList.add(fruit);
    }

    /**
     * 根据业务动态设置具体的优惠策略
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void submitOrder() {
        int money = balance();    //先计算订单总价
        System.out.println("商品总金额为：" + money + "元");
        money = discount.calculate(money);    //根据优惠策略，计算优惠后价格
        System.out.println("优惠减免后：" + money + "元，");
    }

    private int balance() {
        int money = 0;
        for (Fruit fruit : fruitList) {
            money += fruit.price();
        }
        return money;
    }
}
