package com.java.xknowledge.design.create.builder.tuling.v2;

/**
 * 构建模式-链式调用
 * 参考：https://www.bilibili.com/video/BV1Qk4y127rt?p=5
 */
class BuildTest {
    public static void main(String[] args) {
        //链式调用构造
        Product.Builder builder = new Product.Builder().companyName("company1").productName("product1");
        builder.part1("part1").part2("part2");
        Product product = builder.builder();
        System.out.println(product);
    }
}
