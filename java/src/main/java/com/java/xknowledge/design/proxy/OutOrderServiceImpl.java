package com.java.xknowledge.design.proxy;

/**
 * 海外订单
 */
public class OutOrderServiceImpl implements OrderService {
    @Override
    public int savaOrder() {
        System.out.println("下单成功，订单号： 66666666");
        return 66666666;
    }
}
