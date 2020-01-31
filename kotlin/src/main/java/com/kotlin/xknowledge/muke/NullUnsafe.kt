package com.kotlin.study.muke

//声明返回类型是可Null类型
fun getName(): String? {
    return null
}

fun main(args: Array<String>) {
    val name = getName()

    //使用可Null类型，判断Null才执行
    if (name != null) print(name.length)

    //添加?符号，为null不执行
    print(name?.length)

    //!!强制告诉编译器，我充分知道null危险
    print(name!!.length)

}