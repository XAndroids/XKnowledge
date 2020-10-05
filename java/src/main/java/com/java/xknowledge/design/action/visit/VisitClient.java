package com.java.xknowledge.design.action.visit;

import com.java.xknowledge.design.action.visit.fruit.Apple;
import com.java.xknowledge.design.action.visit.fruit.Fruit;
import com.java.xknowledge.design.action.visit.fruit.Orange;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 * 参考：享学《设计模式-访问者模式》
 */
class VisitClient {
    private static SaleVisit saleVisit = new SaleVisit();
    private static List<Fruit> fruitList = new ArrayList<>();

    static {
        fruitList.add(new Orange());
        fruitList.add(new Apple());
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        int total = 0;
        saleVisit.sell(new Apple());

        System.out.println(fruitList.getClass());    //class java.util.ArrayList，fruitList泛型擦除
        for (Fruit fruit : fruitList) {
            System.out.println(fruit.getClass());    //class com.java.xknowledge.design.action.visit.fruit.Orange/Apple，还是原有的类型
            total += saleVisit.sell(fruit);    //由于Java多态方法重载是静态化（遍历集合声明为Fruit类型），saleVisit.sell()无法识别fruit真实类型！！！！
        }
        System.out.println("总价值1：" + total);
    }

    public static void test2() {
        int total = 0;
        for (Fruit fruit : fruitList) {
            total += fruit.accept(saleVisit);    //通过双重分派，通过fruit.accept(salevisit)，sale.visit(this)调用
        }
        System.out.println("总价值2：" + total);
    }
}
