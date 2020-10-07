package com.java.xknowledge.design.create.builder.tuling.v1.builder;

import com.java.xknowledge.design.create.builder.tuling.v1.Product;

/**
 * 特殊建造者实现类
 */
public class SpecialConcreteBuilder implements ProductBuilder {
    private String productName;
    private String companyName;
    private String part1;
    private String part2;

    @Override
    public void buildProductName(String productName) {
        this.productName = productName + "Special";
    }

    @Override
    public void buildCompanyName(String companyName) {
        this.companyName = companyName + "Special";
    }

    @Override
    public void buildPart1(String part1) {
        this.part1 = part1 + "Special";
    }

    @Override
    public void buildPart2(String part2) {
        this.part2 = part2 + "Special";
    }

    @Override
    public Product builder() {
        return new Product(productName, companyName, part1, part2);
    }
}
