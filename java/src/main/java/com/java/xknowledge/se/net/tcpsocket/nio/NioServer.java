package com.java.xknowledge.se.net.tcpsocket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * NioServer实践：实现非阻塞式Socket通信
 * Selector：
 *     SelectableChannel对象多路复用器，所有希望采用非阻塞进行通信的Channel都需要注册到Selector对象
 *     Selector可以同时监控多个SelectableChannel的IO状况，是非阻塞IO的核心：
 *          所有SelectionKey集合
 *          被选择SelectionKey集合
 *          被取消SelectionKey集合
 *     select()/select(timeout)：有需要处理的IO时，此方法返回，将对应的SelectionKey加入被选择的SelectionKey
 *     集合
 * SelectableChannel：
 *     代表可以支持非阻塞IO的Channel对象，可注册到Selector上；
 *     当该Selector上某些SelectionChannel需要处理IO时，调用Selector.select()方法获取数量和selectedKeys()返
 *     回对应的SelectKey集合；
 *     支持阻塞式和非阻塞式，configureBlocking(ture)/isBlocking()返回是否是阻塞式；
 *     不同SelectableChannel支持操作不同
 *         ServerSocketChannel代表ServerSocket，支持OP_ACCEPT；
 *         SocketChannel带包ClientSocket，支持OP_CONNECT，OP_READ，OP_WRITE操作；
 *参考：
 * 《疯狂的Java讲义》
 */
public class NioServer {
    // 用于检测所有Channel状态的Selector
    private Selector selector = null;
    static final int PORT = 30000;
    // 定义实现编码、解码的字符集对象
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws IOException {
        selector = Selector.open();

        // 通过open方法来打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        // 将该ServerSocketChannel绑定到指定IP地址
        server.bind(isa);
        // 设置ServerSocket以非阻塞方式工作
        server.configureBlocking(false);
        // 将server注册到指定Selector对象
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            // 依次处理selector上的每个已选择的SelectionKey
            for (SelectionKey sk : selector.selectedKeys()) {
                // 从selector上的已选择Key集中删除正在处理的SelectionKey
                selector.selectedKeys().remove(sk);      //①

                // 如果sk对应的Channel包含客户端的连接请求，获取客户端SocketChannel，注册Selector处理输入
                if (sk.isAcceptable())        //②
                {
                    // 调用accept方法接受连接，产生服务器端的SocketChannel
                    SocketChannel sc = server.accept();
                    // 设置采用非阻塞模式
                    sc.configureBlocking(false);
                    // 将该SocketChannel也注册到selector
                    sc.register(selector, SelectionKey.OP_READ);
                    // 将sk对应的Channel设置成准备接受其他请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }

                // 如果sk对应的Channel有数据需要读取，读取客户端发送数据，群发所有客户端SocketChannel
                if (sk.isReadable())     //③
                {
                    // 获取该SelectionKey对应的Channel，该Channel中有可读的数据
                    SocketChannel sc = (SocketChannel) sk.channel();
                    // 定义准备执行读取数据的ByteBuffer
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    // 开始读取数据
                    try {
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            content += charset.decode(buff);
                        }
                        // 打印从该sk对应的Channel里读取到的数据
                        System.out.println("读取的数据：" + content);
                        // 将sk对应的Channel设置成准备下一次读取
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                    // 如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                    // 对应的Client出现了问题，所以从Selector中取消sk的注册
                    catch (IOException ex) {
                        // 从Selector中删除指定的SelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }

                    // 如果content的长度大于0，即聊天信息不为空，发送群聊消息，发送所有SocketChannel
                    if (content.length() > 0) {
                        // 遍历该selector里注册的所有SelectionKey
                        for (SelectionKey key : selector.keys()) {
                            // 获取该key对应的Channel
                            Channel targetChannel = key.channel();
                            // 如果该channel是SocketChannel对象
                            if (targetChannel instanceof SocketChannel) {
                                // 将读到的内容写入该Channel中
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NioServer().init();
    }
}
