package com.java.xknowledge.se.net.tcpsocket.senior.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketServer高级实现：ServerThread中，实现了登录，私信和群聊的功能
 * 参考：
 * 《疯狂Java讲义》
 */
class SeniorServer {
    private static final int SERVER_PORT = 30000;
    // 使用CrazyitMap对象来保存每个客户名字和对应输出流之间的对应关系。
    public static SeniorMap<String, PrintStream> clients = new SeniorMap<>();

    public void init() {
        try (
                // 建立监听的ServerSocket
                ServerSocket ss = new ServerSocket(SERVER_PORT)) {
            // 采用死循环来不断接受来自客户端的请求
            while (true) {
                Socket socket = ss.accept();
                new SeniorServerThread(socket).start();
            }
        }
        // 如果抛出异常
        catch (IOException ex) {
            System.out.println("服务器启动失败，是否端口"
                    + SERVER_PORT + "已被占用？");
        }
    }

    public static void main(String[] args) {
        SeniorServer server = new SeniorServer();
        server.init();
    }
}
