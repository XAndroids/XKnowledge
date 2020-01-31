package com.kotlin.study.official.other.collections

fun main(args: Array<String>) {
    //1.可变List集合
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
    //1.不可变List结合
    val readOnlyNumbers: List<Int> = numbers
    println(numbers)
    numbers.add(4)
    //2.readOnlyNumber指向相同的list底层实现，会随list改变
    println(readOnlyNumbers)
    //1.不可变Lst，编译失败
//    readOnlyNumbers.clear();
    numbers.clear()
    println(numbers)

    val strings: MutableSet<String> = mutableSetOf<String>("a", "b", "c")
    strings.add("d")
    println(strings)

    //3.在非关键性代码创建map，可以使用一个简单的mapOf(a to b)来完成
    val stringMap = mapOf(1 to "a", 2 to "b")
    println(stringMap)

    val items = mutableListOf(1, 2, 3)
    val readOnlyItems:List<Int> = items
    //3.toList扩展方法只是复制列表项，因此返回的list保持永远不变
    val itemsCopy = items.toList()
    items.add(4)
    println(items)
    println(itemsCopy)

    //4.List和Set有很多有用的扩展方法值得熟悉
    println(readOnlyItems.first())
    println(readOnlyItems.last())
    println(readOnlyItems.filter { it % 2 == 0 })


    println(items.requireNoNulls())
    if(items.none { it > 6 }) println("No items above 6")
    println(items.firstOrNull())
}

