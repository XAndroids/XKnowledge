package com.java.xknowledge.design.action.template.enjoy;

import com.java.xknowledge.design.action.template.enjoy.shoppingcart.CashShoppingCart;
import com.java.xknowledge.design.action.template.enjoy.shoppingcart.OnlineShoppingCart;
import com.java.xknowledge.design.action.template.enjoy.shoppingcart.ShoppingCart;
import com.java.xknowledge.design.common.fruit.Apple;
import com.java.xknowledge.design.common.fruit.Orange;

/**
 * 模板方法设计模式
 * 参考：享学《设计模式-模板方法设计模式》
 */
class TemplateClient {
    public static void main(String[] args) {
        ShoppingCart onlineShoppingCart = new OnlineShoppingCart();
        onlineShoppingCart.addFruit(new Apple());
        onlineShoppingCart.addFruit(new Orange());
        onlineShoppingCart.submitOrder();

        ShoppingCart cashShoppingCart = new CashShoppingCart();
        cashShoppingCart.addFruit(new Apple());
        cashShoppingCart.addFruit(new Orange());
        cashShoppingCart.submitOrder();
    }
}
