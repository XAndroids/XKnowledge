package com.java.xknowledge.se.jni.invoke;

/**
 * C/C++调用Java代码签名
 */
public class Test {
    public int property = 1;

    //Test的PrintfTest方法提供给C/C++中调用
    public static void PrintfStaticTest() {
        System.out.println("PrintfStaticTest Invoke");
    }

    public void PrintfTest() {
        System.out.println("PrintfTest Invoke");
    }
}
