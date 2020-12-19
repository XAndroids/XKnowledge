package com.java.xknowledge.se.net.socket.multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 基于TCP网络编程2，SocketServer实现，增加了服务端独立线程ServerThread，负责接受和监听是否发送小
 * 参考：
 * 《疯狂的Java讲义》
 */
class MyServer {
    //定义保存所有Socket的ArrayList
    public static ArrayList<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(30000);
        while (true) {
            // 此行代码会阻塞，将一直等待别人的连接
            Socket s = ss.accept();
            socketList.add(s);

            // 每当客户端连接后启动一条ServerThread线程为该客户端服务
            new Thread(new ServerThread(s)).start();
        }
    }
}
