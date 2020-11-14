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

    //成员方法
    public native String HelloWorld();
    //静态成员方法
    public native static String HelloWorldStatic();

    public static void main(String[] args) {
        Register register = new Register();
        //调用成员方法
        System.out.println(register.HelloWorld());
        //调用静态成员方法
        System.out.println(Register.HelloWorldStatic());
    }
}
