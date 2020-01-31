package com.kotlin.study.official.classesandobjects.properties

//1.方法由可见性修饰符
//2.访问修饰符有四中，private protected internal public
//3.包-private 只在文件中可见
private fun bac() {

}

//1.属性有可见性修饰符
//2.默认的可见性是public
internal val a: Int = 0

//2.接口有可见性修饰符
interface A {
    fun function1()
}

//2.类有可见性修饰符
//3.包级-internal只在模块中可见
internal open class B : A {
    //4.对于类和接口，private-类中可见
    private val param1: String = ""
    //4.对于类和接口，protected-子类可见
    protected val param2: String = ""
    //4.对于类和接口，internal-模块可见
    internal val param3: String = ""
    val param4: String = ""

    //1.set有可见性修饰符
    var d: String = ""
        protected set(value) {
            field = value + ""
        }

    override fun function1() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

//5.指定一个类的构造函数的可见性，显示增加constructor关键字
//6.internal意味着成员只在相同的模块内可见，一个模块是指的编译在一起的一套Kotlin文件
private class C internal constructor(a: Int) : B() {
    fun funtion2() {
        //6.局部变量、函数和类都不能有可见性修饰符
        val ab: String
        fun function3() {

        }
        println(param2)
    }
}

fun main(args: Array<String>) {
    B().param3
    C(1).funtion2()
}