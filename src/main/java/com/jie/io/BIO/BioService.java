package com.jie.io.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioService {
    public static void main(String[] args) throws IOException {
        ServerSocket serviceSocket = new ServerSocket(9000);
        Socket accept = serviceSocket.accept();
        System.out.println("连接成功");
        byte[] bytes = new byte[1024];
        while (true){

            int read = accept.getInputStream().read(bytes);
           System.out.println("接受到消息:   "+new String(bytes,0,read));

            }
    }
}
