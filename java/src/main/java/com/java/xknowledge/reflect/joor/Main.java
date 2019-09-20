package com.java.xknowledge.reflect.joor;

import org.joor.Reflect;

public class Main {
    public static void main(String[] args) {
        //熟悉的链式调动
        String result = Reflect.on("java.lang.String").create("Hello world").call("substring", 6).call("toString").get();
        System.out.println(result);

        //FIXME 动态代理新增的逻辑在哪里？？？
        String result2 = Reflect.on("java.lang.String").create("Hello world").as(StringProxy.class).substring(6);
        System.out.println(result2);
    }
}
