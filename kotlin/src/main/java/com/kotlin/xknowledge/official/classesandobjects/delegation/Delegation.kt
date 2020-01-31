package com.kotlin.study.official.classesandobjects.delegation

interface Base {
    val message:String
    fun print()
    fun println()
}

class BaseImpl(val x: Int) : Base {
    override val message = "BaseImpl: x = $x"

    override fun print() {
        print(x)
    }

    override fun println() {
        println(x)
    }
}

//1.Derived类可以通过将所有公有成员都委托给指定对象来实现一个接口Base
class Derived(b: Base) : Base by b {
    //3.这种方式重写的成员不会在委托对象成员中使用，只能访问其自身对接口成员实现
    override val message = "Message of Derived"
    //3.编译器会使用override覆盖的实现而不是委托对象中的
    override fun println() {
        println("abc")
    }
}


fun main(args: Array<String>) {
    val b = BaseImpl(10)
    //2.Derived的超类型类表中by-子句表示b将会Derived中内部存储，并且编译器将生成转发给b的所有Base方法
    b.print()
    b.println()
    println(b.message)

    Derived(b).print()
    Derived(b).println()
    println(Derived(b).message)
}