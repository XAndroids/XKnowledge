package com.java.xknowledge.design.action.chain.discount;

/**
 * 优惠接口
 */
public interface Discount {
    /**
     * 计算优惠后的价格
     *
     * @param money 优惠前的价格
     * @return 优惠后的价格
     */
    int calculate(int money);
}
