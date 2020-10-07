package com.java.xknowledge.design.create.builder.tuling.v2;

/**
 * 要建造的产品
 */
public class Product {
    private final String productName;    //成员是final，只能在构造函数一次性传入
    private final String companyName;
    private final String part1;
    private final String part2;

    public Product(String productName, String companyName, String part1, String part2) {
        this.productName = productName;
        this.companyName = companyName;
        this.part1 = part1;
        this.part2 = part2;
    }

    public String getProductName() {
        return productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPart1() {
        return part1;
    }

    public String getPart2() {
        return part2;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                '}';
    }

    static class Builder {
        private String productName;
        private String companyName;
        private String part1;
        private String part2;

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder part1(String part1) {
            this.part1 = part1;
            return this;
        }

        public Builder part2(String part2) {
            this.part2 = part2;
            return this;
        }

        public Product builder() {
            //todo 可以此验证构建参数
            return new Product(productName, companyName, part1, part2);
        }
    }
}
