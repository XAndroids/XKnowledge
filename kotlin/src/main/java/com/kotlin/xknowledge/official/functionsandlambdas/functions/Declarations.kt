package com.kotlin.study.official.functionsandlambdas.functions

//Kotlin中的函数使用fun关键字声明
fun printLog(tag: String, message: String) {
    println("$tag : $message")
}

fun main(args: Array<String>) {
    //调用函数使用传统方法
    printLog("error", "Exception")
}
