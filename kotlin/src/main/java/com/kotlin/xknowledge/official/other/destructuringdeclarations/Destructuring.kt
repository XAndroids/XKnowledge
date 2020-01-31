package com.kotlin.study.official.other.destructuringdeclarations

data class Person(val name: String, val age: Int)

data class Result(val result: Int, val status: String)

fun foo(): Result {
    return Result(12, "OK")
}

fun main(args: Array<String>) {
    val person = Person("Make", 12)
    val (name, age) = person
    println(name)
    println(age)

    val personList: List<Person> = listOf(Person("Tom", 21), person)
    for ((name, age) in personList) {
        println("name = $name , age = $age")
    }

    val (result, status) = foo()
    println("result = $result , status = $status")

    val mapString = mapOf("name" to "mike", "age" to 21, "city" to "beijign")
    for ((key, value) in mapString) {
        println("key = $key,value = $value")
    }
}