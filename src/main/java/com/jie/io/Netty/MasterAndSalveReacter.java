package com.jie.io.Netty;

import java.io.IOException;

public class MasterAndSalveReacter {
    public static void main(String[] args) throws IOException {
        TCPRractor tcpRractor = new TCPRractor(9000);
        tcpRractor.run();
    }
}
