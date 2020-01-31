package com.kotlin.study.official.other.ranges

fun main(args: Array<String>) {
    for (i in 1..10) {
        println(i)
    }

    for (j in 10 downTo 1) {
        println(j)
    }

    for (k in 1..4 step 2) {
        println(k)
    }

    for (m in 1 until 4) {
        println(m)
    }
}