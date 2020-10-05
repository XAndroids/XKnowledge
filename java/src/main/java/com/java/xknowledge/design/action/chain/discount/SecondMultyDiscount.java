package com.java.xknowledge.design.action.chain.discount;
/**
 * 第二单9折优惠
 */
public class SecondMultyDiscount extends MultyDiscount {
    public SecondMultyDiscount(MultyDiscount nextMultyDiscount) {
        super(nextMultyDiscount);
    }

    @Override
    public int calculate(int money) {
        System.out.println("第二单打9折，");
        Double blance1 = money * 0.9;
        System.out.println("第二单，优惠后价格:" + blance1);
        return super.calculate(blance1.intValue());
    }
}
