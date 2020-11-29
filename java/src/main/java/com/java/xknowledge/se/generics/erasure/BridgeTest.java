package com.java.xknowledge.se.generics.erasure;

import java.lang.reflect.Method;

/**
 * 泛型擦除-桥接方法
 * 参考：bilibili.com/video/BV1xJ411n77R?p=11
 */
class BridgeTest {
    interface Info<T> {
        T info(T t);
    }

    static class InfoImple implements Info<Integer> {

        @Override
        public Integer info(Integer integer) {
            return null;
        }
    }

    public static void main(String[] args) {
        Class<InfoImple> clz = InfoImple.class;
        Method[] declaredMethod = clz.getDeclaredMethods();
        //Info<T>类型擦除后，T info(T t)为Object info(Object t)
        //系统为了保持多态的规范,为接口实现类InfoImple，自动添加了info:Object方法
        //info:Integer
        //info:Object
        for (Method method : declaredMethod) {
            System.out.println(method.getName() + ":" + method.getReturnType().getSimpleName());
        }
    }
}
