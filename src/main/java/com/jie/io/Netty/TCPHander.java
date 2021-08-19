package com.jie.io.Netty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class TCPHander implements Runnable{

    private final SelectionKey register;
    private final SocketChannel sc;
    public TCPHander(SelectionKey register, SocketChannel sc) {
        this.register=register;
        this.sc=sc;
    }

    @Override
    public void run() {
        try {
            System.out.println("aaaaa");
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() throws IOException {
        byte[] bytes = new byte[1024];

        int read = sc.read(ByteBuffer.wrap(bytes));

        System.out.println("接受到消息:   "+new String(bytes,0,read));
        send();
        register.selector().wakeup();

    }

    public void send() throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap("return".getBytes());
        sc.write(buffer);
    }
}
