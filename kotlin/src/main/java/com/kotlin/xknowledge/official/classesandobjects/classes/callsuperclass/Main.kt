package com.kotlin.study.official.classesandobjects.classes.callsuperclass

fun main(args: Array<String>) {
    val base = Child()
    println(base.color)
    base.print()

    val inner = base.Inner()
    inner.color()
}