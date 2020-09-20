package com.java.xknowledge.design.chain;

import com.java.xknowledge.design.chain.discount.FullMultyDiscount;
import com.java.xknowledge.design.chain.discount.HolidayMultyDiscount;
import com.java.xknowledge.design.chain.discount.MultyDiscount;
import com.java.xknowledge.design.chain.discount.NewerMultyDiscount;
import com.java.xknowledge.design.chain.discount.SecondMultyDiscount;
import com.java.xknowledge.design.chain.fruit.Apple;
import com.java.xknowledge.design.chain.fruit.Banana;
import com.java.xknowledge.design.chain.fruit.Fruit;
import com.java.xknowledge.design.chain.fruit.Orange;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式
 * 参考：享学《设计模式-责任链模式》
 */
class ChainCartClient {
    //满减优惠
    private static MultyDiscount multyDiscount = new FullMultyDiscount(null);

    static {
        //创建优惠责任链
        multyDiscount = new NewerMultyDiscount(multyDiscount);
        multyDiscount = new SecondMultyDiscount(multyDiscount);
        multyDiscount = new HolidayMultyDiscount(multyDiscount);
    }

    public static void main(String[] args) {
        //创建购物水果
        List<Fruit> products = new ArrayList<>();
        products.add(new Apple());
        products.add(new Orange());
        products.add(new Banana());

        //创建购物车
        ShoppingCart shoppingCart = new ShoppingCart(products);
        //设置优惠策略
        shoppingCart.setDiscount(multyDiscount);
        //提交订单，计算金额和优惠
        shoppingCart.submitOrder();
    }
}
