package com.java.xknowledge.se.net.socket.senior.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * 高级Sokcet客户端实现：增加了用不登录，私聊和群聊的功能
 * 参考：
 * 《疯狂的Java讲义》
 */
public class SeniorClient {
    private static final int SERVER_PORT = 30000;

    //连接服务器Socket
    private Socket socket;
    //服务器Socket输出流
    private PrintStream ps;
    //服务器Soeckt输入流
    private BufferedReader brServer;

    //键盘输入流
    private BufferedReader keyIn;

    public void init() {
        try {
            // 初始化代表键盘的输入流
            keyIn = new BufferedReader(new InputStreamReader(System.in));

            // 连接到服务器
            socket = new Socket("127.0.0.1", SERVER_PORT);
            // 获取该Socket对应的输入流和输出流
            ps = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String tip = "";
            // 采用循环不断地弹出对话框要求输入用户名
            while (true) {
                String userName = JOptionPane.showInputDialog(tip + "输入用户名");    //①
                // 将用户输入的用户名的前后增加协议字符串后发送
                ps.println(SeniorProtocol.USER_ROUND + userName + SeniorProtocol.USER_ROUND);
                // 读取服务器的响应
                String result = brServer.readLine();
                // 如果用户重复，开始下次循环
                if (result.equals(SeniorProtocol.NAME_REP)) {
                    tip = "用户名重复！请重新";
                    continue;
                }
                // 如果服务器返回登录成功，结束循环
                if (result.equals(SeniorProtocol.LOGIN_SUCCESS)) {
                    break;
                }
            }
        }
        // 捕捉到异常，关闭网络资源，并退出该程序
        catch (UnknownHostException ex) {
            System.out.println("找不到远程服务器，请确定服务器已经启动！");
            closeRs();
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("网络异常！请重新登录！");
            closeRs();
            System.exit(1);
        }

        // 以该Socket对应的输入流启动ClientThread线程
        new SeniorClientThread(brServer).start();
    }

    // 定义一个读取键盘输出，并向网络发送的方法
    private void readAndSend() {
        try {
            // 不断读取键盘输入
            String line = null;
            while ((line = keyIn.readLine()) != null) {
                // 如果发送的信息中有冒号，且以/开头，则认为想发送私聊信息
                if (line.indexOf(":") > 0 && line.startsWith("/")) {
                    line = line.substring(1);
                    ps.println(SeniorProtocol.PRIVATE_ROUND +
                            line.split(":")[0] + SeniorProtocol.SPLIT_SIGN
                            + line.split(":")[1] + SeniorProtocol.PRIVATE_ROUND);
                } else {
                    ps.println(SeniorProtocol.MSG_ROUND + line
                            + SeniorProtocol.MSG_ROUND);
                }
            }
        }
        // 捕捉到异常，关闭网络资源，并退出该程序
        catch (IOException ex) {
            System.out.println("网络通信异常！请重新登录！");
            closeRs();
            System.exit(1);
        }
    }

    // 关闭Socket、输入流、输出流的方法
    private void closeRs() {
        try {
            if (keyIn != null) {
                ps.close();
            }
            if (brServer != null) {
                ps.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (socket != null) {
                keyIn.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SeniorClient client = new SeniorClient();
        client.init();
        client.readAndSend();
    }
}
