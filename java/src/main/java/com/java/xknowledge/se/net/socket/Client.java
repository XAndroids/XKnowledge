package com.java.xknowledge.se.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 基于TCP网络编程，SocketClient实现
 * 运行：
 * 来自服务器的数据：您好，您收到了服务器的新年祝福！
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1" , 30000);   //①
        // 将Socket对应的输入流包装成BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 进行普通IO操作
        String line = br.readLine();
        System.out.println("来自服务器的数据：" + line);
        // 关闭输入流、socket
        br.close();
        socket.close();
    }
}

