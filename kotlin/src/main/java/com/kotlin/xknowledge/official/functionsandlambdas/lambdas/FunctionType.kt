package com.kotlin.xknowledge.official.functionsandlambdas.lambdas

//函数类型，参考：https://www.kotlincn.net/docs/reference/lambdas.html#%E5%87%BD%E6%95%B0%E7%B1%BB%E5%9E%8B

/**
 * (A,B)->C函数类型
 * 在Int集合numList的每个元素执行一个行为doFunc
 * @param numList 执行行为的集合
 * @param doFunc 使用 (Int) -> Unit函数类型声明参数，执行的方法：输入Int类型，返回Unit类型
 */
fun doInIntList(numList: List<Int>, doFunc: (Int) -> Int) {
    for (num in numList) {
        val result = doFunc(num)
        println("$num doFunc, = $result")
    }
}

/**
 * 可选择性的包含函数的参数(intArgs:Int)->Unit，用于表明参数的含义
 */
fun doInIntList2(numList: List<Int>, doFunc2: (intArgs: Int) -> Unit) {
    for (num in numList) {
        doFunc2(num)
    }
}

/**
 * 如果将函数类型置位可空，则使用圆括号：((Int) -> Unit)?
 */
fun doInIntList3(numList: List<Int>, doFunc3: ((Int) -> Unit)?) {
    for (num in numList) {
        println("$num doFunc3")
        //对用null函数类型，需要Null安全调用
//        doFunc3?.let { it(num) }
        doFunc3?.invoke(num)
    }
}

/**
 * 箭头函数可以使用圆括号进行接合 () -> ((Int) -> Unit))
 * 并且默认是右结合，同 () -> (Int) -> Unit)
 */
fun doInIntList4(numList: List<Int>, doFunc4: () -> (Int) -> Unit) {
    for (num in numList) {
        val result = doFunc4.invoke()
        result.invoke(num)
    }
}

//可以通过typealias给函数类型起一个别名
typealias intHandler = (Int) -> Int

fun doInIntList5(numList: List<Int>, doFunc5: intHandler) {
    for (num in numList) {
        val result = doFunc5(num)
        println("$result doFunc5")
    }
}

fun main(args: Array<String>) {
    val intList = listOf<Int>(1, 3, 5, 7, 9)

    doInIntList(intList) {
        it * 2
    }

    doInIntList2(intList) {
        println("$it doFunc2")
    }

    //传入null和非null类型
    doInIntList3(intList, null)
    doInIntList3(intList) {
        println("$it doFunc3 Not Null")
    }

    //funResult作为结果返回
    val funResult: (Int) -> Unit = {
        println("$it doFunc4")
    }
    doInIntList4(intList) {
        funResult
    }

    doInIntList5(intList) {
        it + 100
    }
}