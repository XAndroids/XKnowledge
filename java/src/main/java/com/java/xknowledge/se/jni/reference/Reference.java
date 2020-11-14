package com.java.xknowledge.se.jni.reference;

/**
 * JNI引用
 * 参考：享学一期《JNI调用java函数与JNI引用》
 */
class Reference {
    static {
        System.load("/Users/qitmac0000562/CLionProjects/CPlusKnowledge/cmake-build-debug/libreferenceLib.dylib");
    }

    public native void callJavaStaticMethod();

    public native void callJavaInstanceMethod();

    public static void main(String[] args) {
        Reference reference = new Reference();
        reference.callJavaInstanceMethod();
        reference.callJavaStaticMethod();
    }
}
