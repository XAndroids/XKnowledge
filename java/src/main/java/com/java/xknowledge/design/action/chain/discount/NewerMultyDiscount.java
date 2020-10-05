package com.java.xknowledge.design.action.chain.discount;
/**
 * 首次购
 */
public class NewerMultyDiscount extends MultyDiscount {
    public NewerMultyDiscount(MultyDiscount nextMultyDiscount) {
        super(nextMultyDiscount);
    }

    @Override
    public int calculate(int money) {
        if (money > 100) {
            System.out.println("首次购买减20元，");
            money = money - 20;
            System.out.println("首次购，优惠后价格:" + money);
        }
        return super.calculate(money);
    }
}
