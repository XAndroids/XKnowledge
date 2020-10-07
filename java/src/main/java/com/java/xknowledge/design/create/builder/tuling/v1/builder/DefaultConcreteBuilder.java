package com.java.xknowledge.design.create.builder.tuling.v1.builder;

import com.java.xknowledge.design.create.builder.tuling.v1.Product;

/**
 * 默认的建造者
 */
public class DefaultConcreteBuilder implements ProductBuilder {
    private String productName;
    private String companyName;
    private String part1;
    private String part2;

    @Override
    public void buildProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public void buildCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void buildPart1(String part1) {
        this.part1 = part1;
    }

    @Override
    public void buildPart2(String part2) {
        this.part2 = part2;
    }

    @Override
    public Product builder() {
        return new Product(productName, companyName, part1, part2);
    }
}
