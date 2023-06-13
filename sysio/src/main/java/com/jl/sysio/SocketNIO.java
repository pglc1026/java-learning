package com.jl.sysio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class SocketNIO {

    public static void main(String[] args) throws Exception {
        List<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9090));
        ssc.configureBlocking(false);

        while (true) {
            // 接受客户端连接
            Thread.sleep(1000);
            SocketChannel client = ssc.accept();    // 不会阻塞 -1 NULL
            // accept 调用内核了：没有客户端连接，返回 -1 NULL，有客户端连接，返回连接的客户端的fd，client object

            if (client == null) {
                System.out.println("null......");
            } else {
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("client.port: " + port);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

            // 遍历已经连接进来的客户端，能不能读写数据
            for (SocketChannel sc : clients) {
                int num = sc.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    System.out.println(sc.socket().getPort() + " : " + new String(bytes));
                    buffer.clear();
                }
            }
        }

    }

}
