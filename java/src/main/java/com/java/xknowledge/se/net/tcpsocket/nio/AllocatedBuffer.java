package com.java.xknowledge.se.net.tcpsocket.nio;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;

/**
 * Buffer分配实践：网络通信使用直接内存allocateDirect，应用读写使用堆上内存allocate
 * 运行：
 * ----------Test allocate--------
 * before allocate:4739854336
 * buffer = java.nio.HeapByteBuffer[pos=0 lim=2000000 cap=2000000]
 * after allocate:4737757184
 * directBuffer = java.nio.DirectByteBuffer[pos=0 lim=2000000 cap=2000000]
 * after direct allocate:4737757184
 * ----------Test wrap--------
 * java.nio.HeapByteBuffer[pos=0 lim=32 cap=32]
 * java.nio.HeapByteBuffer[pos=10 lim=20 cap=32]
 *
 * Process finished with exit code 0
 * 参考：
 * 享学2《网络协议总结一》
 */
class AllocatedBuffer {
    public static void main(String[] args) {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean)
                ManagementFactory.getOperatingSystemMXBean();


        System.out.println("----------Test allocate--------");
        System.out.println("before allocate:"
                + osmxb.getFreePhysicalMemorySize());

        /*堆上分配*/
        ByteBuffer buffer = ByteBuffer.allocate(2000000);
        System.out.println("buffer = " + buffer);
        System.out.println("after allocate:"
                + osmxb.getFreePhysicalMemorySize());

        /* 这部分用的直接内存*/
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(2000000);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct allocate:"
                + osmxb.getFreePhysicalMemorySize());

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);

        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
    }
}
