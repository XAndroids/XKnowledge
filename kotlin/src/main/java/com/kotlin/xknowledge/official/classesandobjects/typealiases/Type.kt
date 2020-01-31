package com.kotlin.study.official.classesandobjects.typealiases

//1.类型别名有助于缩短较长的泛型类型
typealias IntSet = Set<Int>

//2.可以为内部类和嵌套类创建新的名称
class A {
    inner class AInner
}

class B {
    inner class BInner
}

typealias AInner1 = A.AInner

fun main(args: Array<String>) {
    var intSets: IntSet? = setOf(1, 2, 3)
    intSets = null
    println(intSets.toString())

    val aInner = A().AInner1()
    val bInner = B().BInner()
}