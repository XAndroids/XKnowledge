package com.java.xknowledge.design.create.builder.tuling.v1.builder;

import com.java.xknowledge.design.create.builder.tuling.v1.Product;

/**
 * 建造者接口，定义了建造每一个部分的方法
 */
public interface ProductBuilder {
    void buildProductName(String productName);

    void buildCompanyName(String companyName);

    void buildPart1(String part1);

    void buildPart2(String part2);

    Product builder();
}
