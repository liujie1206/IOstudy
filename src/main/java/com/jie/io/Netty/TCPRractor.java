package com.jie.io.Netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TCPRractor implements Runnable{

    private final ServerSocketChannel ssc;
    private final Selector selector;

    public TCPRractor(int port) throws IOException{

            selector = Selector.open();
            ssc=ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        ssc.socket().bind(address);
        ssc.configureBlocking(false);
        SelectionKey register = ssc.register(selector, SelectionKey.OP_ACCEPT);
        register.attach(new Acceptor(ssc));


    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            System.out.println("wait for a accept");
            try {
                selector.select();

            }catch (IOException e){
                System.out.println(e);
            }
            System.out.println("accept success join queue");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                dispatch(key);
                iterator.remove();
            }
        }
    }

    private void dispatch(SelectionKey key) {

        Runnable accept = (Runnable) key.attachment();
        if (accept!=null){
            accept.run();
        }
    }
}
