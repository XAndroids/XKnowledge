package com.kotlin.study.official.other.thisexpressions

//隐式标签@A
class A {
    //隐式标签@B
    inner class B {
        //隐式标签@foo
        fun Int.foo() {
            val a = this@A
            val b = this@B

            val c = this
            val v1 = this@foo

        }
    }
}

fun main(args: Array<String>) {

}