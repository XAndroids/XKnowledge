package com.java.xknowledge.design.action.command.handler;

/**
 * 热门Handler没有抽象接口
 */
public class HotHandler {
    public String getHots() {
        System.out.println("返回热门商品列表");
        return "热门商品列表";
    }
}
