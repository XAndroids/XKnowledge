package com.kotlin.study.official.functionsandlambdas.lambdas


fun main(args: Array<String>) {
    val items = listOf(1, 2, 3, 4, 5)

    //Lambdas表达式是花括号括起来的代码
    items.fold(0, {
        //如果一个lambdas表达式由参数，前面是参数，后面跟"->"
        acc: Int, i: Int ->
        println("acc = $acc, i = $i")
        val result = acc + i
        println("result = $result")
        //lambdas的最后一个表达式是返回值
        result
    })

    print(items)
}