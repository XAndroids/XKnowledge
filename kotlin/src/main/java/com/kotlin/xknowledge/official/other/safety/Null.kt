package com.kotlin.study.official.other.safety

fun main(args: Array<String>) {
    //定义不可空类型a
    var a: String = "abc"
    //如果复制为null，则编译报错
//    a = null
    //当为不可空类型的时候，可以放心调用length，不需要做非null判断
    println(a.length)

    //定义可空类型b
    var b: String? = null
    //可以复制null
    println(b)
    //做null条件检查，可以通过编译
    if (b != null) {
        //可空类型，如果直接调用会报错
        print(b.length)
    }

    //使用安全调用操作符
    println(b?.length)
    //链式调用，那个环节为null返回null
    var employee = Employee(null)
    println(employee.department?.head?.name)
    if (employee.department?.head?.name.equals("呼呼")) {
        println("true")
    } else {
        println("false")
    }

    //左边链式跳过，右边不求值
    var employee1 = Employee(Department(Head("啊哈")))
    employee1.department?.head?.name = getHeadName()

    //Elvis操作符，为null返回右侧表达式
    val c: String? = "cdf"
    //当且仅当左侧为null的时候，才会求右侧的值
    println(c?.length ?: getRightValue())
    //return 和throw可以在elvis操作符右侧
//    println(c?.length ?: throw IllegalArgumentException("expected"))

    //非空断言运算符，将任何值转换为非空类型
    println(c!!.length)

    //as?安全的类型转换，如果不成功则返回null
    val bString: String = "1"
    val aInt: Int? = bString as? Int
    println(aInt)
}

fun getHeadName(): String? {
    println("getHeadName")
    return "呼呼"
}

fun getRightValue(): Int {
    println("getRightValue")
    return -1
}


