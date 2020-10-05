package com.java.xknowledge.design.action.chain.discount;

/**
 * 责任链优惠
 */
public abstract class MultyDiscount implements Discount {
    //通过下个优惠，链接多个优惠责任链
    protected MultyDiscount nextMultyDiscount;

    public MultyDiscount(MultyDiscount nextMultyDiscount) {
        this.nextMultyDiscount = nextMultyDiscount;
    }

    @Override
    public int calculate(int money) {
        //计算下一个优惠的价格
        if (nextMultyDiscount != null) {
            return this.nextMultyDiscount.calculate(money);
        }
        System.out.println("优惠后价格:" + money);
        return money;
    }
}
