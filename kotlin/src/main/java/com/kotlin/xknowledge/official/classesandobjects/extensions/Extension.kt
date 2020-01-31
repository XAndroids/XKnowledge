package com.kotlin.study.official.classesandobjects.extensions

fun String.appendEnd(end: String): String {
    return this + end
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun <T> MutableList<T>.swap1(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

open class C
class D : C()

fun D.printFoo() = "D"
fun C.printFoo() = "C"

fun printFoo(c: C) {
    println(c.printFoo())
}

class E {
    fun foo() {
        println("member")
    }
}

fun E.foo() {
    println("extension")
}

fun E.foo(foo: String) {
    println("extension" + foo)
}

fun Any?.toString(): String {
    if (this == null) return "null"
    return toString()
}

val <T> List<T>.lastIndex: Int
    get() = size - 1

val String.len: Int
    get() = length

class F {
    companion object
}

fun F.Companion.foo() {
    println("F.Companion.foo()")
}

//class G {
//    fun bar() {
//        println("bar")
//    }
//}
//
//class H {
//    fun bzs() {
//        println("bzs")
//    }
//
//    fun G.foo() {
//        bar()
//        bzs()
//    }
//
//    fun caller(d: G) {
//        d.foo()
//    }
//}

fun main(args: Array<String>) {
    println("haha".appendEnd("hehe"))
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3, 4)
    numbers.swap(1, 2)
    println(numbers.toString())

    val strings: MutableList<String> = mutableListOf("1", "2", "3", "4")
    strings.swap1(2, 3)
    println(strings.toString())

    printFoo(D())

    E().foo()
    E().foo("ex")

    val string: String? = null
    println(string.toString())

    println(numbers.lastIndex)

    println("haha".len)

    F.foo()

//    H().caller(G())
//    G.foo()
}