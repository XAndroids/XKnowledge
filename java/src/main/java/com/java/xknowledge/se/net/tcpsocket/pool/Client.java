package com.java.xknowledge.se.net.tcpsocket.pool;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 类说明：Bio通信的客户端
 * 运行：
 * Hello,Lance
 * Process finished with exit code 0
 * 参考：享学2《操作系统和JDK对网络通信的实现》
 */
public class Client {

    public static void main(String[] args) throws IOException {
        //客户端必备
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 10002);

        try {
            socket = new Socket();
            socket.connect(serverAddr);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            outputStream.writeUTF("Lance");
            outputStream.flush();

            System.out.println(inputStream.readUTF());
        } finally {
            if (socket != null) socket.close();
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
        }


    }

}
