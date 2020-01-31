package com.kotlin.study.official.functionsandlambdas.functions

//函数参数使用Pascal表示法定义，即name:type，参数使用逗号隔开，每个参数必须有显示类型
//函数参数可以有默认值，当省略参数时使用默认值，默认值通过类型后面的=及给出的值来定义
fun add(a: Int = 1, b: Int): Int {
    return a + b
}

class Person(var name: String, val age: Int) {
    infix fun setNameWithPre(name: String) {
        this.name = "pre $name"
    }

    fun printString() {
        //当使用中缀表示法在当前接受者上调用时，要显示的使用this
        this setNameWithPre "haha"
        //中缀函数总是要求制定接受者与参数
        setNameWithPre("hehe")

        println("$name , $age")
    }
}

open class A {
    //如果一个函数不返回任何有用的值，它的返回类型是Unit
    //Unit是一种只有一个值——Unit的类型，这个值不需要显示的返回
    open fun print(message: String = "A"): Unit {
        println(message)
    }
}

class B : A() {
    //覆盖方法总是使用与基类型方法相同的默认，当覆盖一个带有默认参数值的方法时，必须从签名中省略默认值
    override fun print(message: String) {
        println(message)
    }
}

//如果一个默认参数在一个无默认值的参数前，那么该默认值只能通过使用命名参数调用该函数调用
fun appendString(a: String = "a", b: String): String {
    return a + b
}

fun reformatInfo(name: String, age: Int = 0, sex: String = "meal"): String {
    return "name=$name,age=$age,sex=$sex"
}

//具有代码块的函数必须显示指定返回类型，除非它们返回Unit
//Kotlin不推断具有代码块的返回类型
fun arraySize(vararg strings: String): Int {
    var count = 0
    for (t in strings) {
        count++
    }
    return count
}

//当函数返回单个表达式事，可以省略花括号，在=号后面指定代码体即可
//如果返回值类型编译器可以推断时，显示声明类型是可选的
fun double(a: Int) = a * 2

//函数的参数（通常是最后一个）可以用vararg修饰表示，允许将可变数量的参数传递给函数
fun sum(preInt: Int, vararg ints: Int, lastInt: Int): Int {
    var sum = preInt
    for (i in ints) {
        sum += i
    }
    return sum + lastInt
}

//标有infix关键字的函数，可以使用中缀表示法（忽略调用的点和圆括号）
infix fun Int.reformat(a: Int) {
    println("$a" + Int.MAX_VALUE)
}

fun main(args: Array<String>) {
    val result = add(2, 3)
    //调用成员函数使用点表示方法
    Person("Mike", 34).printString()

    add(b = 1)

    B().print()

    val resultString = appendString(b = "bParam")
    println(resultString)

    //使用默认参数调用
    println(reformatInfo("MeiMei"))
    //使用非默认参数调用
    println(reformatInfo("Jack", 23, "man"))
    //使用命令参数调用，更具有可读性
    println(reformatInfo(sex = "woman", name = "Mike"))
    //当函数调用混用位置参数和命名参数的时候，所有位置参数必须放在第一个命名参数之前
    println(reformatInfo("MM", age = 34, sex = "nam"))

    //可以通过使用星号操作符将可变参数以命名参数传入
    println(arraySize(strings = *arrayOf("a", "b", "c")))

    //在调用Java函数时不能使用命名参数语法，因为Java字节码并不总是保留函数参数的名称
//    Math.abs(a = 12)

    double(3)

    //只有一个参数可以标注为vararg，如果vararg参数不是列表的最后一个，则使用命名参数语法传递其后的参数
    //调用函数vararg函数时，可以一个一个地传参数
    println(sum(1, 2, 3, 4, lastInt = 5))

    val ints1 = intArrayOf(1, 2, 23)
    //如果有一个数组，使用伸展操作符*传值
    println(sum(1, *ints1, lastInt = 4))

    //中缀函数必须满足：必须是成员函数或者扩展函数，必须只有一个函数，参数不的接受可变数量的参数，且不能有默认值
    3 reformat 23

    //中缀函数优先级低于算术操作符、类型转换以及rangTo操作符
    //另一方面，中缀表达式优先级高于布尔操作符&& || is-等
    3 reformat 23 + 42
}


