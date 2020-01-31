package com.kotlin.study.official.classesandobjects.dataclasses

data class Person(val name: String, val sex: String) {
    var age: Int = 0
}

fun main(args: Array<String>) {
    val person = Person("Mike", "man")
    println(person.toString())
    val personCopy = person.copy(sex = "woman")
    println(personCopy.toString())

    val (name, sex) = person
    println("person name = $name, age = $sex")
}