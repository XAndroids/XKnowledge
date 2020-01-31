package com.kotlin.study.official.classesandobjects.classes

//使用class关键字声明类
//类声明，由类名、类头（指定其参数类型、主构函数）以及由花括号包围的类体构成
class Invoice(var name: String) {
    init {
        println("invoice init")
    }
}


//类头和类体都是可选的
//如果一个类没有类体，则可以省略花括号
class Empty

//一个类可以有1以及1个或者多个次构造函数
//主构造函数是类头的一部分，跟在类名的后面
class Student constructor(firstName: String, lastName: String)

//如果主构函数没有任何注解或者可见修饰符，可以省略这个constructor关键字
class Person(name: String)

//主构函数不包含任何代码，初始化代码放在init关键字作为的初始化块中
//初始化块按照他们出现在类体中的顺序执行，与属性初始化器交织在一起
//和普通属性一样，主构函数中声明的属性可以是可变var或者可读var的
class Teacher(var name: String) {
    //主构函数可以在初始化中使用，也可以在属性初始化器中使用
    val firstName = name.subSequence(0, 2)

    init {
        name = "黎明"
        println("firstName = $firstName")
        println("name = $name")
    }

    val lastName = name.toUpperCase()

    init {
        name = "刘德华"
        println("lastName = $lastName")
        println("name = $name")
    }
}

//如果构造函数有注解或者可见修饰符，则constructor关键字是必须的，并且这些修饰符在他前面
//属性不在主构函数中声明的时候，只能在初始化块和属性声明中使用
class Customer public constructor(var name: String, var age: Int) {
    val firstName: String = name.substring(0, 1)

    init {
        //初始化块中的代码实际上会成为主构函数的一部分，委托给主构函数会作为次构函数的第一条语句
        println("init $name.length")
    }

    //类可以声明前缀是constructor的次构函数
    //如果类有一个主构函数，每个次构函数都需要委托给主构函数，直接委托
    constructor(name: String, age: Int, sex: String) : this(name, age)

    //通过别的次构函数间接委托，委托同一个类的另一个构造函数用this关键字
    constructor(name: String, age: Int, sex: String, country: String) : this(name, age, sex)

    fun test() {
        println("age = $age")
    }
}


//如果一个非抽象类没有声明任何（主、次）构函数，会有一个生成的不带参数的无参数构造函数
//如果你不希望有一个共有构造函数，需要声明一个带非默认可见性的空主构函数
class Animal constructor()

//要声明一个显示的超类型，我们把类型放到类头的冒号后面
//如果派生类有一个主构造函数，基类型可以（并且必须）用基类的主构造函数擦书就地初始化
open class Food(color: String)

class Apple(color: String) : Food(color)
class Orange : Food {
    //如果类没有主构函数，那么每个次构函数必须使用super关键字初始化其基类型
    constructor(color: String) : super(color)
}


//将open修饰符添加到final类的成员上不起作用
open class Base {
    open fun print() {
        println("Base")
    }
}

open class Child : Base() {
    //在Kotln中可覆盖的成员（称之为开放），以及覆盖后成员需要显示override修饰
    override fun print() {
        println("Child")
    }
}

class Grandson : Child() {
    //标记为override的成员本身就是开放的，它可以在子类中覆盖，如果你想禁止，使用final关闭
    override fun print() {
        println("Grandson")
    }
}

open class Pen {
    //覆盖属性和覆盖方法类似，在超类中声明然后再派生类初中重新声明的属性必须以override开头，
    //并且它们是兼容类型
    //声明的属性可以由具有初始化器的属性，或者具有getter方法的属性覆盖
    open val color: String = ""
        get() {
            println("pen color get")
            return field
        }
}

class Pencil : Pen() {
    //可以用一个var属性覆盖一个val属性，反之不行
    //因为一个val属性本质上声明了一个getter方法，而将其覆盖为var只是在子类中额外声明一个setter方法
    override var color: String = ""
        get() {
            println("pencil color get")
            return field
        }
}

//可以在主构函数中使用override关键字作为属性声明的一部分
class BallPen(override var color: String) : Pen()

fun main(args: Array<String>) {
    //创建类的实例，像普通函数一样调用构造函数
    //Kotlin中没有new关键字
    val teacher = Teacher("吴雨辰")
    val customer = Customer("王佳林", 26)
    val customer1 = Customer("彭呈祥", 30, "男")
    customer.test()
    val animal = Animal()

    val pencil = Pencil()
    val ballPen = BallPen("圆珠笔")
    println(pencil.color)
    println(ballPen.color)
}







