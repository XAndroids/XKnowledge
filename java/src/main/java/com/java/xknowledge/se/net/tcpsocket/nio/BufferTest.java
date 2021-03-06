package com.java.xknowledge.se.net.tcpsocket.nio;

import java.nio.ByteBuffer;

/**
 * ByteBuffer实践
 * 运行：
 *------Test get and put-------------
 * before flip()java.nio.HeapByteBuffer[pos=6 lim=32 cap=32]
 * before get():java.nio.HeapByteBuffer[pos=0 lim=6 cap=32]
 * a
 * after get():java.nio.HeapByteBuffer[pos=1 lim=6 cap=32]
 * after get(dst, 0, 2):java.nio.HeapByteBuffer[pos=3 lim=6 cap=32]
 * dst:bc        
 * --------Test 绝对读写-------
 * before put(byte):java.nio.HeapByteBuffer[pos=0 lim=32 cap=32]
 * after put(byte):java.nio.HeapByteBuffer[pos=1 lim=32 cap=32]
 * after put(2,(byte) 'c'):java.nio.HeapByteBuffer[pos=1 lim=32 cap=32]
 * z c                             
 * c
 * after get(index):java.nio.HeapByteBuffer[pos=3 lim=6 cap=32]
 * --------Test Clear And Compact--------
 * before clear:java.nio.HeapByteBuffer[pos=1 lim=32 cap=32]
 * after clear:java.nio.HeapByteBuffer[pos=0 lim=32 cap=32]
 * x                               
 * before compact:java.nio.HeapByteBuffer[pos=4 lim=32 cap=32]
 * after flip:java.nio.HeapByteBuffer[pos=0 lim=4 cap=32]
 * 还有数据未读，个数:4
 * after compact:java.nio.HeapByteBuffer[pos=4 lim=32 cap=32]
 * abcd                            
 * --------Test rewind--------
 * before rewind:java.nio.HeapByteBuffer[pos=10 lim=15 cap=32]
 * before rewind:java.nio.HeapByteBuffer[pos=0 lim=15 cap=32]
 * --------Test mark AND reset----------
 * buffer = java.nio.HeapByteBuffer[pos=0 lim=20 cap=20]
 * before reset:java.nio.HeapByteBuffer[pos=10 lim=20 cap=20]
 * after reset:java.nio.HeapByteBuffer[pos=5 lim=20 cap=20]
 *
 * Process finished with exit code 0
 * 参考：
 * 享学2《网络协议总结一》
 */
class BufferTest {
    public static void main(String[] args) {
        System.out.println("------Test get and put-------------");
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put((byte) 'a')//0
                .put((byte) 'b')//1
                .put((byte) 'c')//2
                .put((byte) 'd')//3
                .put((byte) 'e')//4
                .put((byte) 'f');//5
        System.out.println("before flip()" + buffer);
        /* 转换为读取模式*/
        buffer.flip();
        System.out.println("before get():" + buffer);
        System.out.println((char) buffer.get());
        System.out.println("after get():" + buffer);

        /* position移动两位*/
        byte[] dst = new byte[10];
        buffer.get(dst, 0, 2);
        System.out.println("after get(dst, 0, 2):" + buffer);
        System.out.println("dst:" + new String(dst));

        /*绝对读写*/
        System.out.println("--------Test 绝对读写-------");
        ByteBuffer bb = ByteBuffer.allocate(32);
        System.out.println("before put(byte):" + bb);
        System.out.println("after put(byte):" + bb.put((byte) 'z'));
        /* put(2,(byte) 'c')不改变position的位置*/
        bb.put(2, (byte) 'c');
        System.out.println("after put(2,(byte) 'c'):" + bb);
        System.out.println(new String(bb.array()));

        /* get(index)不影响position的值*/
        System.out.println((char) buffer.get(2));
        System.out.println("after get(index):" + buffer);

        System.out.println("--------Test Clear And Compact--------");
        ByteBuffer buffer2 = ByteBuffer.allocate(32);
        buffer2.put((byte) 'x');
        System.out.println("before clear:" + buffer2);
        buffer2.clear();
        System.out.println("after clear:" + buffer2);
        System.out.println(new String(buffer2.array()));
        /*放入4个字节，position移动到下个可写入的位置，也就是4*/
        buffer2.put("abcd".getBytes());
        System.out.println("before compact:" + buffer2);
        buffer2.flip();//将position设回0，并将limit设置成之前position的值*/
        System.out.println("after flip:" + buffer2);
        /*compact()方法将所有未读的数据拷贝到Buffer起始处。*/
        /* 然后将position设到最后一个未读元素正后面。*/
        System.out.println("还有数据未读，个数:" + buffer2.remaining());
        buffer2.compact();
        System.out.println("after compact:" + buffer2);
        System.out.println(new String(buffer2.array()));

        System.out.println("--------Test rewind--------");
        buffer.clear();
        buffer.position(10);/*移动position到10*/
        buffer.limit(15);/*限定最大可写入的位置为15*/
        System.out.println("before rewind:" + buffer);
        buffer.rewind();/*将position设回0*/
        System.out.println("before rewind:" + buffer);

        System.out.println("--------Test mark AND reset----------");
        buffer = ByteBuffer.allocate(20);
        System.out.println("buffer = " + buffer);
        buffer.clear();
        buffer.position(5);/*移动position到5*/
        buffer.mark();/*记录当前position的位置*/
        buffer.position(10);/*移动position到10*/
        System.out.println("before reset:" + buffer);
        buffer.reset();/*复位position到记录的地址*/
        System.out.println("after reset:" + buffer);
    }
}
