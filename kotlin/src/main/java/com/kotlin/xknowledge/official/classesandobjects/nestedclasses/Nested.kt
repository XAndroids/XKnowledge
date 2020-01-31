package com.kotlin.study.official.classesandobjects.nestedclasses

class Outer {
    val a: String = "a"
    fun foo() {
        println("foo")
    }

    //1.类可以嵌套在其它类中
    class Inner1 {
//        fun fooIner() {
//            println(a)
//            foo()
//        }
    }

    //2.类可以标记为inner以便能够访问外部类的成员，内部类会带有一个外部类的对象引用
    inner class Inner {
        fun fooIner() {
            println(a)
            foo()
        }
    }
}

fun main(args: Array<String>) {
    val inner = Outer().Inner().fooIner()
}