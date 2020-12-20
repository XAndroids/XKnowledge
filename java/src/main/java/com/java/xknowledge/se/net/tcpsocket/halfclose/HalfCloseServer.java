package com.java.xknowledge.se.net.tcpsocket.halfclose;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 半关闭服务端：
 * shutdownOutput：单独关闭输出流，但是还可以输入流接收客户端的数据
 * 参考：
 * 《疯狂的Java讲义》
 */
class HalfCloseServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(30000);
        //接收客户端的链接
        Socket socket = ss.accept();

        //向客户端发送数据
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println("服务器的第一行数据");
        ps.println("服务器的第二行数据");

        //关闭socket的输出流，表明输出数据已经结束
        socket.shutdownOutput();
        //下面语句将输出false，表明socket还未关闭。
        System.out.println(socket.isClosed());

        //继续接收客户端发送的数据
        Scanner scan = new Scanner(socket.getInputStream());
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        scan.close();
        socket.close();
        ss.close();
    }
}
