package com.java.xknowledge.se.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * UDP网络通信实践：
 * DatagramSocket：不维护状态，不能产生IO流，接受和发送数据报；
 *     DatagramSocket()/DatagramSocket(poot)/DatagramSocket(port,ipaddress)：创建DatagramSocket实例，指
 *     定端口一般用来服务端，应用开启某个端口服务接收和发送数据
 *     receive(datagramsocket)/send(datagramsocket)：发送和接收数据；
 * DatagramSocket：代表数据报
 *     DatagramSocket(byte[],length)/DatagramSocket(byte[],length,ip,port)/DatagramSocket(byte[],offert
 *     ,length):指定数据报数据，长度和目标ip端口等；
 * 参考：
 * 《疯狂的Java讲义》
 */
public class UdpClient {
    // 定义发送数据报的目的地
    public static final int DEST_PORT = 30000;
    public static final String DEST_IP = "127.0.0.1";
    // 定义每个数据报的最大大小为4K
    private static final int DATA_LEN = 4096;
    // 定义接收网络数据的字节数组
    byte[] inBuff = new byte[DATA_LEN];
    // 以指定字节数组创建准备接受数据的DatagramPacket对象
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    // 定义一个用于发送的DatagramPacket对象
    private DatagramPacket outPacket = null;

    public void init() throws IOException {
        // 创建一个客户端DatagramSocket，使用随机端口
        try (DatagramSocket socket = new DatagramSocket()) {
            // 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组
            outPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName(DEST_IP), DEST_PORT);
            // 创建键盘输入流
            Scanner scan = new Scanner(System.in);
            // 不断读取键盘输入
            while (scan.hasNextLine()) {
                // 将键盘输入的一行字符串转换字节数组
                byte[] buff = scan.nextLine().getBytes();
                // 设置发送用的DatagramPacket里的字节数据
                outPacket.setData(buff);
                // 发送数据报
                socket.send(outPacket);
                // 读取Socket中的数据，读到的数据放在inPacket所封装的字节数组里。
                socket.receive(inPacket);
                System.out.println(new String(inBuff, 0, inPacket.getLength()));
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        new UdpClient().init();
    }
}
