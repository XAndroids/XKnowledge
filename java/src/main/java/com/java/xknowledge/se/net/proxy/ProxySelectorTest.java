package com.java.xknowledge.se.net.proxy;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ProxySelector实践：根据"业务需要"返回特定的对应的代理服务器
 * 参考：
 * 《疯狂的Java讲义》
 */
public class ProxySelectorTest {
    // 下面是代理服务器的地址和端口，
    // 随便一个代理服务器的地址和端口
    final String PROXY_ADDR = "100.20.24.227";
    final int PROXY_PORT = 13414;
    // 定义需要访问的网站地址
    String urlStr = "https://www.baidu.com";

    public void init() throws IOException, MalformedURLException {
        // 注册默认的代理选择器
        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("无法连接到指定代理服务器！");
            }

            // 根据"业务需要"返回特定的对应的代理服务器
            @Override
            public List<Proxy> select(URI uri) {
                // 本程序总是返回某个固定的代理服务器。
                List<Proxy> result = new ArrayList<>();
                result.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_ADDR, PROXY_PORT)));
                return result;
            }
        });

        URL url = new URL(urlStr);
        // 没有指定代理服务器、直接打开连接
        URLConnection conn = url.openConnection();   //①
        // 设置超时时长。
        conn.setConnectTimeout(3000);
        // 通过代理服务器读取数据的Scanner
        try (Scanner scan = new Scanner(conn.getInputStream());
             PrintStream ps = new PrintStream("index.htm")) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                // 在控制台输出网页资源内容
                System.out.println(line);
                // 将网页资源内容输出到指定输出流
                ps.println(line);
            }
        }
    }

    public static void main(String[] args) throws IOException, MalformedURLException {
        new ProxySelectorTest().init();
    }
}