package com.java.xknowledge.design.create.builder.tuling.v1;

import com.java.xknowledge.design.create.builder.tuling.v1.builder.DefaultConcreteBuilder;
import com.java.xknowledge.design.create.builder.tuling.v1.builder.ProductBuilder;
import com.java.xknowledge.design.create.builder.tuling.v1.builder.SpecialConcreteBuilder;

/**
 * 构建模式
 * 参考：https://www.bilibili.com/video/BV1Qk4y127rt?p=5
 */
class BuildTest {
    public static void main(String[] args) {
        ProductBuilder defaultConcreteBuilder = new DefaultConcreteBuilder();
        Director director = new Director(defaultConcreteBuilder);
        Product product = director.makeProduct("Product", "Company",
                "Part1", "Part2");
        System.out.println(product.toString());

        ProductBuilder specialConcreteBuilder = new SpecialConcreteBuilder();
        Director director2 = new Director(specialConcreteBuilder);
        Product product2 = director2.makeProduct("Product", "Company",
                "Part1", "Part2");
        System.out.println(product2.toString());
    }
}
