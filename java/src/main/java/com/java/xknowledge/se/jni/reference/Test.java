package com.java.xknowledge.se.jni.reference;

/**
 * JNI引用
 */
public class Test {
    public int property = 1;

    //Test的PrintfTest方法提供给C/C++中调用
    public static void PrintfStaticTest() {
        System.out.println("PrintfStaticTest Invoke Reference");
    }

    public void PrintfTest() {
        System.out.println("PrintfTest Invoke Reference");
    }
}
