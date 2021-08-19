package com.jie.io.Netty;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TCPSubReactor implements Runnable{
    private Selector selector;
    private ServerSocketChannel ssc;
    private int i;
    private Boolean restart=true;


    public TCPSubReactor(Selector selector, ServerSocketChannel ssc, int i) {
        this.selector=selector;
        this.ssc=ssc;
        this.i=i;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){

        while (!Thread.interrupted() && !restart){
            System.out.println("wait for a read event");

            try {
                if (selector.select()==0) {
                    continue;
                }


            }catch (IOException e){
                System.out.println(e);
            }
            System.out.println("a read event happend");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                dispatch(key);
                iterator.remove();
            }
        }
    }}

    private void dispatch(SelectionKey key) {

        Runnable accept = (Runnable) key.attachment();
        if (accept!=null){
            accept.run();
        }
    }

    public void setRestart(boolean b) {
        this.restart=b;
    }
}
