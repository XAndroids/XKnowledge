package com.kotlin.study.official.classesandobjects.interfaces

//1.Kotlin接口与Java 8类似，即包含抽象方法的声明，也包含实现；
//1.与抽象类不同的是，接口无法保存状态，可以有属性但是必须声明为抽象或提供访问器实现
interface MyInterface {
    //3.可以在接口中定义属性，要么是抽象的，要么提供访问器的实现
    val a: Int
    //3.不能有幕后字段，因此访问器不能引用它
    val b: String get() = "bbb"

    fun function1() {

    }

    fun funtion2(param1: String): Int
}

//4.一个接口可以从其他接口派生
interface ChildInterface : MyInterface {
    //4.也声明新的函数与属性
    val childString: String

    fun functionChild()

    //4.从而即提供基类型的实现，
    override fun funtion2(param1: String): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

//2.一个类或者对象可以实现一个或者多个接口
class Child : MyInterface {
    override val a: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun funtion2(param1: String): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class Other : ChildInterface {
    override val a: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val childString: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun functionChild() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

//5.实现多个接口时，可能会遇到一个方法继承多个实现的问题
interface A {
    fun name()
    fun foo() {
        println("Foo A")
    }
}

interface B {
    fun age() {
        println("age B")
    }

    fun foo() {
        println("foo B")
    }
}

class C : A, B {
    override fun age() {
        println("age C")
    }

    override fun name() {
        super<A>.foo()
        println("name C")
    }

    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }
}

fun main(args: Array<String>) {
    val myInterface = Child()
    val other = Other()

    val a = C()
    a.age()
    a.name()
    a.foo()

    com.kotlin.study.official.classesandobjects.properties.B()

}