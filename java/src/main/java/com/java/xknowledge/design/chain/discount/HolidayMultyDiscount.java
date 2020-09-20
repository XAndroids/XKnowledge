package com.java.xknowledge.design.chain.discount;

/**
 *  假日一律减5元
 */
public class HolidayMultyDiscount extends MultyDiscount {
    public HolidayMultyDiscount(MultyDiscount nextMultyDiscount) {
        super(nextMultyDiscount);
    }

    @Override
    public int calculate(int money) {
        //计算自己优惠的价格
        if (money > 20) {
            System.out.println("假日一律减5元，");
            money = money - 5;
            System.out.println("假日，优惠后价格:" + money);
        }

        //计算优惠责任链上的价格
        return super.calculate(money);
    }
}
