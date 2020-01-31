package com.kotlin.study.official.classesandobjects.classes.callsuperclass

class Child : Base() {
    //派生类的代码可以使用super关键子字调用其超类的属性
    override val color: String
        get() {
            return super.color + ",Child Color"
        }

    //派生类的代码可以使用super关键字调用其超类的函数
    override fun print() {
        super.print()
        println("Child print...")
    }

    inner class Inner {
        fun color() {
            //在一个内部类中访问外部类的超类，可以通过外部类名限定的super关键字来实现：super@Outer
            println(super@Child.color)
            println(color)
        }
    }
}