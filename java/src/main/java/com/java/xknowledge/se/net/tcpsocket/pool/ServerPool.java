package com.java.xknowledge.se.net.tcpsocket.pool;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：Bio通信的服务端（线程池实现），使用线程池管理客户端连接处理子线程，虽然解决了一个客户端连接，一个线程的问
 * 题。但是受系统线程数量的限制；
 * 运行：
 * Hello,Lance
 * Process finished with exit code 0
 * 参考：享学2《操作系统和JDK网络通信实现》
 */
public class ServerPool {

    private static ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) throws IOException {
        //服务端启动必备
        ServerSocket serverSocket = new ServerSocket();
        //表示服务端在哪个端口上监听
        serverSocket.bind(new InetSocketAddress(10002));
        System.out.println("Start Server ....");
        try {
            while (true) {
                //一个客户端连接，创建线程池的新子线程等待执行
                executorService.execute(new ServerTask(serverSocket.accept()));
            }
        } finally {
            serverSocket.close();
        }
    }

    //每个和客户端的通信都会打包成一个任务，交个一个线程来执行
    private static class ServerTask implements Runnable {

        private Socket socket = null;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            //实例化与客户端通信的输入输出流
            try (ObjectInputStream inputStream =
                         new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream =
                         new ObjectOutputStream(socket.getOutputStream())) {

                //接收客户端的输出，也就是服务器的输入
                String userName = inputStream.readUTF();
                System.out.println("Accept client message:" + userName);

                //服务器的输出，也就是客户端的输入
                outputStream.writeUTF("Hello," + userName);
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
