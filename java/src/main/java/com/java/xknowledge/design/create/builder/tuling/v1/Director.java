package com.java.xknowledge.design.create.builder.tuling.v1;

import com.java.xknowledge.design.create.builder.tuling.v1.builder.ProductBuilder;

class Director {
    private ProductBuilder productBuilder;

    public Director(ProductBuilder productBuilder) {
        this.productBuilder = productBuilder;
    }

    public Product makeProduct(String productName, String companyName, String part1, String part2) {
        //构建前有更多的控制，比如参数校验等等
        if (productName == null && productName.length() == 0) {
            productName = "null!!!";
        }

        //控制过程有更多的控制，比如先后顺序，先构建part1/2，在构建name；
        productBuilder.buildPart1(part1);
        productBuilder.buildPart2(part2);
        productBuilder.buildProductName(productName);
        productBuilder.buildCompanyName(companyName);
        return productBuilder.builder();
    }
}
