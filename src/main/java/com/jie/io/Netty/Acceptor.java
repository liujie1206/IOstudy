package com.jie.io.Netty;

import sun.dc.pr.PRError;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements  Runnable{

    private final ServerSocketChannel ssc;
    private int index=0;
    private int core=2;
    private TCPSubReactor[] h= new TCPSubReactor[core];
    private Thread[] t= new Thread[core];
    private Selector[] s= new Selector[core];

    public Acceptor(ServerSocketChannel ssc) throws IOException {
        this.ssc = ssc;
        for (int i = 0; i < core; i++) {
            s[i] = Selector.open();
            h[i] = new TCPSubReactor(s[i],ssc,i);
            t[i] = new Thread(h[i]);
            t[i].start();


        }

    }

    @Override
    public synchronized void run() {
        try {
            SocketChannel sc = ssc.accept();
            System.out.println("dispatch task=="+ t[index].getId());
            if (sc!=null) {
                sc.configureBlocking(false);
               h[index].setRestart(true);
                s[index].wakeup();

                SelectionKey register = sc.register(s[index], SelectionKey.OP_READ);
                s[index].wakeup();
                h[index].setRestart(false);
                register.attach(new TCPHander(register,sc));
                if(++index == s.length){
                    index=0;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
