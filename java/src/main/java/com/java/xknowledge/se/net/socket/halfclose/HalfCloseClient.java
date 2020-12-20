package com.java.xknowledge.se.net.socket.halfclose;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 半关闭客户端
 * 参考：
 * 《疯狂的Java讲义》
 */
class HalfCloseClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 30000);
        //连接服务端后，接收服务端返回的数据
        Scanner scan = new Scanner(s.getInputStream());
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }

        //向服务端发送数据
        PrintStream ps = new PrintStream(s.getOutputStream());
        ps.println("客户端的第一行数据");
        ps.println("客户端的第二行数据");
        ps.close();
        scan.close();
        s.close();
    }
}
