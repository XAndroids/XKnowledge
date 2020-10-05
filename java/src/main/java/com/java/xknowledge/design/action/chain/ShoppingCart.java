package com.java.xknowledge.design.action.chain;

import com.java.xknowledge.design.action.chain.discount.Discount;
import com.java.xknowledge.design.action.chain.fruit.Fruit;

import java.util.List;

/**
 * 购物车
 */
class ShoppingCart {
    //购买的水果
    private List<Fruit> products;
    //优惠，通过责任链可以链接多个优惠
    private Discount discount;

    public ShoppingCart(List<Fruit> products) {
        this.products = products;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void submitOrder() {
        //计算商品总金额
        int money = balance();
        System.out.println("商品总金额为：" + money + "元");
        //通过优惠责任链，计算优惠后的金额
        money = discount.calculate(money);
        System.out.println("优惠减免后：" + money + "元，");

    }

    private int balance() {
        int money = 0;
        System.out.println("商品清单：");
        for (Fruit fruit : products) {
            fruit.draw();
            money = money + fruit.price();
        }
        return money;
    }
}
