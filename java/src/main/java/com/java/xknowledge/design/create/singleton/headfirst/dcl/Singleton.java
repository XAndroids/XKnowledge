package com.java.xknowledge.design.create.singleton.headfirst.dcl;

//
// Danger!  This implementation of Singleton not
// guaranteed to work prior to Java 5
//

/**
 * 双重锁检查
 */
public class Singleton {
    //为什么volatile，参考：https://juejin.cn/post/6844903772892692487
    private volatile static Singleton uniqueInstance;    //volatile抑制指令重排，避免空判断失效
    private int a;

    {
        //但实际多次测试并没有出现
        //Thread = Thread-97,singleton = Singleton{a=100}
        //Thread = Thread-88,singleton = Singleton{a=100}
        //Thread = Thread-87,singleton = Singleton{a=100}
        //Thread = Thread-99,singleton = Singleton{a=100}
        //Thread = Thread-84,singleton = Singleton{a=100}
        //Thread = Thread-89,singleton = Singleton{a=100}
        //Thread = Thread-75,singleton = Singleton{a=100}
        //Thread = Thread-73,singleton = Singleton{a=100}
        //Thread = Thread-98,singleton = Singleton{a=100}
        //Thread = Thread-68,singleton = Singleton{a=100}
        //Thread = Thread-93,singleton = Singleton{a=100}
        //Thread = Thread-65,singleton = Singleton{a=100}
        //Thread = Thread-92,singleton = Singleton{a=100}

        //从编译结果看，17-24行没有出现质量重排，它是先进行实例化，再存入到静态变量instance中
        //如果我们使用一个基于直接访问对象的编译器（如HotSpot默认编译器），这个地方不加volatile关键字也不会出现问题。
        //而如果我们使用一个基于句柄方式访问对象的编译器（如Symantec JIT），不加volatile关键字可能会导致重排序，返回一个未初始化完成的实例。
        // qitmac0000562  ~/XCodeProjects/XProjects/XKnowledge/java/src/main/java/com/java/xknowledge/design/create/singleton/headfirst/dcl   master ●  javap -l -v Singleton.class
        //Classfile /Users/qitmac0000562/XCodeProjects/XProjects/XKnowledge/java/src/main/java/com/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton.class
        //  Last modified 2020-11-21; size 1167 bytes
        //  MD5 checksum c9f25e103f8c7ef56b0a8bd3316ac6cb
        //  Compiled from "Singleton.java"
        //public class com.java.xknowledge.design.create.singleton.headfirst.dcl.Singleton
        //  minor version: 0
        //  major version: 52
        //  flags: ACC_PUBLIC, ACC_SUPER
        //Constant pool:
        //   #1 = Methodref          #18.#38        // java/lang/Object."<init>":()V
        //   ... ...
        //  #61 = Utf8               (C)Ljava/lang/StringBuilder;
        //{
        //  public static com.java.xknowledge.design.create.singleton.headfirst.dcl.Singleton getInstance();
        //    descriptor: ()Lcom/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton;
        //    flags: ACC_PUBLIC, ACC_STATIC
        //    Code:
        //      stack=2, locals=2, args_size=0
        //         0: getstatic     #8                  // Field uniqueInstance:Lcom/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton;
        //         3: ifnonnull     37
        //         6: ldc           #9                  // class com/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton
        //         8: dup
        //         9: astore_0
        //        10: monitorenter
        //        11: getstatic     #8                  // Field uniqueInstance:Lcom/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton;
        //        14: ifnonnull     27
        //        17: new           #9                  // class com/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton    //new: 在java堆上为对象分配内存空间，并将地址压入操作数栈顶；
        //        20: dup                                                                                                               //dup：复制操作数栈顶值，并将其压入栈顶，也就是说此时操作数栈上有连续相同的两个对象地址
        //        21: invokespecial #10                 // Method "<init>":()V                                                          //invokespecial：用于调用一些需要特殊处理的实例方法，包括实例初始化方法、私有方法和父类方法。
        //        24: putstatic     #8                  // Field uniqueInstance:Lcom/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton;//putstatic：从栈顶取值，存入静态变量中
        //        27: aload_0
        //        28: monitorexit
        //        29: goto          37
        //        32: astore_1
        //        33: aload_0
        //        34: monitorexit
        //        35: aload_1
        //        36: athrow
        //        37: getstatic     #8                  // Field uniqueInstance:Lcom/java/xknowledge/design/create/singleton/headfirst/dcl/Singleton;
        //        40: areturn
        //      Exception table:
        //         from    to  target type
        //            11    29    32   any
        //            32    35    32   any
        //      LineNumberTable:
        //        line 29: 0
        //        line 30: 6
        //        line 31: 11
        //        line 32: 17
        //        line 34: 27
        //        line 36: 37
        a = 100;//如果出现指令重排，则有可能出现a = 0的单例对象
    }

    private Singleton() {
        try {
            Thread.sleep(100);//模拟初始化时间，尝试复现指令重排问题
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {    //该检查避免，锁同步的消耗
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    //为什么需要加volatile修饰uniqueInstance？
                    //uniqueInstance = new Singleton();
                    //可以分解为以下三个步骤
                    //1.memory=allocate();// 分配内存 相当于c的malloc
                    //2.ctorInstanc(memory) //初始化对象
                    //3.s=memory //设置s指向刚分配的地址
                    //上述三个步骤可能会被重排序为 1-3-2，也就是：
                    //1.memory=allocate();// 分配内存 相当于c的malloc
                    //3.s=memory //设置s指向刚分配的地址
                    //2.ctorInstanc(memory) //初始化对象
                    //如果出现指令重排，可能执行1、3后，另外一个线程执行 if (uniqueInstance == null)为true，返回一个没有初始化的单例对象
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "a=" + a +
                '}';
    }
}
