package com.java.xknowledge.design.action.strategy.discount;

/**
 * 优惠策略接口，定义计算优惠后价格方法
 */
public interface Discount {
    int calculate(int money);
}
