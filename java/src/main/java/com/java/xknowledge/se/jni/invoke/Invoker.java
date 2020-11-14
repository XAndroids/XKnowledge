package com.java.xknowledge.se.jni.invoke;

/**
 * 从C/C++调用Java的类、属性和方法
 * 参考： 享学一期《JNI签名问题与访问java成员函数》
 */
class Invoker {
    static {
        System.load("/Users/qitmac0000562/CLionProjects/CPlusKnowledge/cmake-build-debug/libinvokeLib.dylib");
    }

    //在C/C++中调用Test类的PrintTest静态方法
    public native String invoke();

    public static void main(String[] args) {
        //调用native invoke方法间接调用Test的PrintTest方法
        System.out.println(new Invoker().invoke());
    }
}
