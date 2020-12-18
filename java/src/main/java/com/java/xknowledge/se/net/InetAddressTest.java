package com.java.xknowledge.se.net;

import java.net.InetAddress;

/**
 * InetAddress实践：InetAddress提供是否可达，获取ip地址等方法，代表IP地址
 * 运行：
 * crazyit是否可达：false
 * 185.199.111.153
 * 本机是否可达：true
 * localhost
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class InetAddressTest {
    public static void main(String[] args) throws Exception {
        // 根据主机名来获取对应的InetAddress实例
        InetAddress ip = InetAddress.getByName("www.crazyit.org");
        // 判断是否可达
        System.out.println("crazyit是否可达：" + ip.isReachable(2000));
        // 获取该InetAddress实例的IP字符串
        System.out.println(ip.getHostAddress());

        // 根据原始IP地址来获取对应的InetAddress实例
        InetAddress local = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        System.out.println("本机是否可达：" + local.isReachable(5000));
        // 获取该InetAddress实例对应的全限定域名
        System.out.println(local.getCanonicalHostName());
    }
}
