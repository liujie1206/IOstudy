package com.jie.io.NIO;

import com.jie.io.NIO.TCPRractor;

import java.io.IOException;

public class OneReacter {
    public static void main(String[] args) throws IOException {
        TCPRractor tcpRractor = new TCPRractor(9000);
        tcpRractor.run();
    }
}
