package com.java.xknowledge.design.action.template.shoppingcart;

public class OnlineShoppingCart extends ShoppingCart {
    @Override
    protected void pay(int money) {
        System.out.println("线上支付:" + money);
    }
}
