package com.java.xknowledge.se.jni.dynamic;

class Register {
    public native void dynamicFunc1();

    static{
        System.load();
    }
}
