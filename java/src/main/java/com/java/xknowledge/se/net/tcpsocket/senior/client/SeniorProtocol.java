package com.java.xknowledge.se.net.tcpsocket.senior.client;

/**
 * 高级协议：定义了用户登录和私聊消息标识，返回结果
 * 参考：
 * 《疯狂的Java讲义》
 */
public interface SeniorProtocol {
    //定义协议字符串的长度
    int PROTOCOL_LEN = 2;

    //下面是一些协议字符串，服务器和客户端交换的信息
    //都应该在前、后添加这种特殊字符串。
    //发送用户聊天消息
    String MSG_ROUND = "§γ";
    //发送用户登录信息
    String USER_ROUND = "∏∑";

    //登录返回成功
    String LOGIN_SUCCESS = "1";
    //重复登录
    String NAME_REP = "-1";

    //私有信息
    String PRIVATE_ROUND = "★【";
    String SPLIT_SIGN = "※";
}
