package com.java.xknowledge.design.action.chain.discount;

/**
 * 满减
 */
public class FullMultyDiscount extends MultyDiscount {

    public FullMultyDiscount(MultyDiscount nextMultyDiscount) {
        super(nextMultyDiscount);
    }

    @Override
    public int calculate(int money) {
        if (money > 200) {
            System.out.println("优惠满减20元，");
            money = money - 20;
            System.out.println("满减，优惠后价格:" + money);
        }
        return super.calculate(money);
    }
}
