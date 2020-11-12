package com.java.xknowledge.se.jni.static1;

/**
 * JNI静态注册
 * 参考： 享学一期《JNI开发之JNI编译与运行》
 */
class Register {
    static {
        System.load("/Users/qitmac0000562/CLionProjects/CPlusKnowledge/cmake-build-debug" +
                "/libfirstLib.dylib");
    }
    public native String HelloWorld();

    public static void main(String[] args) {
        Register register = new Register();
        System.out.print(register.HelloWorld());
    }
}
