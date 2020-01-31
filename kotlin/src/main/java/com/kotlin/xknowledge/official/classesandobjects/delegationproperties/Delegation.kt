package com.kotlin.study.official.classesandobjects.delegationproperties

import kotlin.reflect.KProperty
import kotlin.lazy
import kotlin.properties.Delegates

//1.有一些常见的属性类型，可以在每次需要的时候手动实现它们，把它们山西爱你一次病放入一个库更好
//为了涵盖这些，Kotlin只是委托属性
//2.语法是：val/var <属性名>:<类型名> by <表达式>
//by后面的表达式是该委托，因为属性对应的get()（与set()会被委托给它们的getValue()与setValue()方法）
class Delegation {
    var name: String by DelegateName()

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    var address: String by Delegates.observable("<no name>") { _, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }
}

//2.属性的委托不需要实现任何接口，但是需要提供一个getValue()函数（与setValue()-对var属性）
class DelegateName {
    operator fun getValue(delegation: Delegation, property: KProperty<*>): String {
        return "$delegation, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(delegation: Delegation, property: KProperty<*>, s: String) {
        println("$s has been assigned to '${property.name}' in $delegation.")
    }
}


class User(val map: MutableMap<String, Any?>) {
    val name: String by map
    var age: Int by map
}

fun main(args: Array<String>) {
    val delegation = Delegation()
    delegation.name = "a"
    println(delegation.name)
    println(delegation.lazyValue)
    println(delegation.lazyValue)

    delegation.address = "beijing"
    delegation.address = "shanghai"

    val user = User(mutableMapOf("name" to "Mike", "age" to 22))
    println(user.name)
    println(user.age)
}