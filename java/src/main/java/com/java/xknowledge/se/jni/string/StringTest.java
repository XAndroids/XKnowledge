package com.java.xknowledge.se.jni.string;

/**
 * JNI中的字符换
 * 参考： 享学一期《JNI签名问题与访问java成员函数》
 */
class StringTest {
    static {
        System.load("/Users/qitmac0000562/CLionProjects/CPlusKnowledge/cmake-build-debug" +
                "/libstringLib.dylib");
    }

    //native方法调用传递String类型text，在C/C++内拼接新字符换后返回String
    public native static String sayHello(String text);

    public static void main(String[] args) {
        String text = sayHello("Hello");
        System.out.println(text);
    }

}
