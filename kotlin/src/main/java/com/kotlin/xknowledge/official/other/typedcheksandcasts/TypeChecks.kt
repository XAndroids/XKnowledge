package com.kotlin.study.official.other.typedcheksandcasts

fun main(args: Array<String>) {
    val obj: Any = "Hello"
    //1.通过使用is操作符检查对象是否是给定的类型
    if (obj is String) {
        //2.在许多情况下，不需要在Kotlin中显示转换操作符，编译器会跟踪不可变的值的is-检查以及显示转换
        println(obj.length)
    }

    //3.编译器构聪明，能够知道如果方向检查导致返回，那么转换是安全的
    if (obj !is String) return
    println(obj.length)

    //4.在&& 和 || 的右侧
    val string: Any = "World"
//    if (string !is String || obj.length == 0) return
    if (string is String && string.length > 0) {
        println(string.length)
    }

    //5.智能转换在when用于when和while也一样
    var x: Any = 1
    when (x) {
        is Int -> println(x + 1)
        is String -> println(x.length + 1)
        is IntArray -> println(x.sum())
    }

    //6.为了避免抛出异常，可以使用安全转换操作符as?,它可以失败的时候返回null
    val a: Any = 1
    val b: String? = a as? String
}