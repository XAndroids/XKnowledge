package com.java.oop;

/**
 * (抽象)父类和接口具有相同方法，调用实现
 *
 */
class TestClass extends TestAbstractClass implements TestInterface {

    //1.1 对于test方法，接口和抽象类都没有具体实现，故调用的就是子类的test()方法
    @Override
    public void test() {
        System.out.println("TestClass");
    }

    //2.1 对于test2()，抽象类实现了test2()方法，由于接口声明，也必须实现接口方法，此时即是实现接口方法也是覆盖父类方法
    @Override
    public void test2() {
        super.test2();
        System.out.println("TestClass2");
    }

    public static void main(String[] args) {
        //1.2 调用方法时，自由子类实现具体方法，故调用的是子类方法
        TestInterface testClass = new TestClass();
        testClass.test();
        //2.2 调用方法时，由于父类实现接口方法，子类覆盖接口方法，调用方法为子类，除非子类方法中super调用父类方法
        testClass.test2();

        TestAbstractClass testClass2 = new TestClass();
        testClass2.test();
        testClass2.test2();

        TestClass testClass3 = new TestClass();
        testClass3.test();
        testClass3.test2();
    }
}
