package com.java.xknowledge.design.action.template.enjoy.shoppingcart;

/**
 * 现金支付购物车，只现金支付实现，不关心整个下单流程
 */
public class CashShoppingCart extends ShoppingCart {
    @Override
    protected void pay(int money) {
        System.out.println("现金支付:" + money);
    }
}
