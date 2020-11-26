package com.java.xknowledge.se.generics.method;

/**
 * 泛型方法实践
 */
//泛型类GenericsMethod
class GenericsMethod<T> {
    private T key;//泛型类的成员

    //泛型类的方法
    public GenericsMethod(T key) {
        this.key = key;
    }

    //泛型类的方法
    public T getKey() {
        return key;
    }

    //泛型类的方法
    public void setKey(T key) {
        this.key = key;
    }

    /**
     * 这个才是真正的泛型方法，首先在public与返回值之间的<E>比不可少，表明这是一个泛型方法，并且声明一个泛型E
     * 这个E可以用在泛型方法的任意位置，泛型的数量也可以任意多
     */
    public <E> E showKeyName(GenericsMethod<E> genericsMethod) {
        return genericsMethod.key;
    }

    public static void main(String[] args) {
        GenericsMethod<String> genericsMethod = new GenericsMethod<>("");
        genericsMethod.setKey("abc");
        //调用泛型类方法，传入类型是String
        System.out.println("key = " + genericsMethod.getKey());
        //调用房型方法，传入类型是Integer
        System.out.println("keyName = " + genericsMethod.showKeyName(new GenericsMethod<>(1)));
    }
}
