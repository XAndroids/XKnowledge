package com.java.xknowledge.se.net;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLDecoder实践：用于完成普通字符串和application/x-www-form-urlencoded MIME字符转换，包含中文普通字符需要转换，提供encode()和decode()方法；
 * 运行：
 * 疯狂java
 * %B7%E8%BF%F1Android%BD%B2%D2%E5
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class URLDecoderTest {
    public static void main(String[] args) throws Exception {
        // 将application/x-www-form-urlencoded字符串
        // 转换成普通字符串
        // 其中的字符串直接从图17.3所示窗口复制过来
        String keyWord = URLDecoder.decode("%B7%E8%BF%F1java", "GBK");
        System.out.println(keyWord);

        // 将普通字符串转换成
        // application/x-www-form-urlencoded字符串
        String urlStr = URLEncoder.encode("疯狂Android讲义", "GBK");
        System.out.println(urlStr);
    }
}
