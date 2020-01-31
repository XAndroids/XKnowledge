package com.kotlin.study.official.functionsandlambdas.functions

//Kotlin中函数可以在文件顶层声明
fun sum(a: Int, b: Int): Int {
    val c = 3
    //Kotlin支持声明局部函数，即一个函数在另外一个函数内部
    //局部函数可以访问外部函数的局部变量
    fun printInt(a: Int) {
        println(a + c)
    }

    printInt(4)

    return a + b
}

class Foot {
    //成员函数是对象内部定义的函数
    fun printFoot() {
        print("Foot Print")
    }
}

fun main(args: Array<String>) {
    sum(1, 2)
    //成员函数以点表示法调用
    Foot().printFoot()
}


