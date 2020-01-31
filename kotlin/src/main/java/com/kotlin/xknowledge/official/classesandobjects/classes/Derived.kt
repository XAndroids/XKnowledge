package com.kotlin.study.official.classesandobjects.classes

import com.kotlin.study.official.classesandobjects.extensions.F
import com.kotlin.study.official.classesandobjects.extensions.foo

open class Car(val name: String) {
    //派生类的新实例过程中，第一步完成基类的初始化
    init {
        println("init Base")
    }

    open val size: Int = name.length.also { println("Init size in Base:$it") }
}

//在之前有对基类构造函数参数的求值
class Tesla(name: String, val type: String) : Car(name.capitalize().also { println("Argument for Base: $it") }) {
    init {
        println("init Child")
    }

    override val size: Int = (super.size + type.length).also { println("Initializing size in Derived: $it") }
}

fun main(args: Array<String>) {
    val tesla = Tesla("Child", "Mode X")
    F.foo()
}