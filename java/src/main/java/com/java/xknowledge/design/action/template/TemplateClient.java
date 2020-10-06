package com.java.xknowledge.design.action.template;

import com.java.xknowledge.design.action.strategy.fruit.Apple;
import com.java.xknowledge.design.action.strategy.fruit.Orange;
import com.java.xknowledge.design.action.template.shoppingcart.CashShoppingCart;
import com.java.xknowledge.design.action.template.shoppingcart.OnlineShoppingCart;
import com.java.xknowledge.design.action.template.shoppingcart.ShoppingCart;

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
