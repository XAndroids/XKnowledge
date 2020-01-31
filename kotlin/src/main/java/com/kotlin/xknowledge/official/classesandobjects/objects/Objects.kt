package com.kotlin.study.official.classesandobjects.objects

interface OnClickListener {
    fun onClick()
}

//2.如果超类有一个构造函数，则必须传递适当的构造函数参数给它，多个超类型可以由跟在冒号后面逗号分隔的列表指定
open class OnEventListener(val log: String) {
    open fun onEventLog(log: String) {
        println(log)
    }
}

fun setOnclick(listener: OnClickListener) {
    listener.onClick()

    //3.任何时候，我们只需要"一个对象而已"，并不需要特殊超类型，可以如下简单写
    val ab = object {
        val a: String = "aaa"
    }

    println(ab.a)
}

open class C {
    //4.匿名对象可以用作只在本地和私有作用域中声明的类型
    private fun foo() = object {
        val x: String = "x"
    }

    //5.如果你使用匿名对象作为公有函数的返回类型或用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型
    //如果你没有声明任何超类型，就会是Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x
        println(x1)
        //6.在匿名对象中添加的成员将无法访问
        println(publicFoo())
    }
}

//7.单例模式在一些场景中中很有用，成为对象声明，它总在object关键字后
//9.对象声明可以有超类型
object Instance : C() {
    var count: Int = 0

    fun add() {
        count++
    }

    fun remove() {
        count--
    }
}

class MyClass {
    //10.类内部的对象声明可以用companion关键字标记
    //12.可以省略伴生对象的名称，这种情况下使用名称Companion
    //13.即使伴生对象的成员看起来像其它语言的静态成员，在运行时他们仍然是真是对象实例成员，而且还可以实现接口
    companion object Factory : OnClickListener {
        override fun onClick() {

        }

        fun create(): MyClass = MyClass()
    }
}

fun main(args: Array<String>) {
    //1.匿名内部类，创建一个队某个类做了轻微改动的对象，而不是为之显示的声明新的子类
    setOnclick(object : OnClickListener, OnEventListener("Event") {
        override fun onEventLog(log: String) {
            println(log)
        }

        override fun onClick() {
            println("onClick")
        }
    })

    C().bar()

    //8.如需引用对象，直接使用其名称即可
    Instance.add()
    println(Instance.count)
    Instance.remove()
    println(Instance.bar())

    //11.该伴生对象的成员可以通过只使用类名作为限定符来调用
    //13.其自身所用的类的名称，可以作用对该类的伴生对象
    MyClass.create()
//    MyClass.Companion.create()
}