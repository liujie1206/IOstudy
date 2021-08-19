package com.jie.io.NIO;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements  Runnable{

    private final ServerSocketChannel ssc;
    private final Selector selector;
    public Acceptor(Selector selector, ServerSocketChannel ssc) {
        this.ssc = ssc;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = ssc.accept();
            System.out.println("accept successs");
            if (sc!=null) {
                sc.configureBlocking(false);
                SelectionKey register = sc.register(selector, SelectionKey.OP_READ);
                selector.wakeup();
                register.attach(new TCPHander(register,sc));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
