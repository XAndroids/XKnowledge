package com.java.xknowledge.design.action.command.handler;

/**
 * 打折Handler没有抽象接口
 */
public class DiscountHandler {
    public String getDiscount() {
        System.out.println("返回打折商品列表");
        return "打折商品列表";
    }
}
