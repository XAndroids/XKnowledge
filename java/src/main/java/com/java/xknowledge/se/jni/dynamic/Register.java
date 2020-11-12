package com.java.xknowledge.se.jni.dynamic;

/**
 * JNI动态注册
 * 参考： 享学一期《JNI开发之JNI编译与运行》
 */
class Register {
    static {
        System.load("/Users/qitmac0000562/CLionProjects/CPlusKnowledge/cmake-build-debug" +
                "/libfirstdyLib.dylib");
    }

    //动态注册方法
    native void dynamicFunc1();

    native String dynamicFunc2();

    native int getRandom();

    public static void main(String[] args) {
        Register register = new Register();
        register.dynamicFunc1();
        System.out.println(register.dynamicFunc2());
        System.out.println(register.getRandom());
    }
}
