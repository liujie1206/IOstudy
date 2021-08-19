package com.jie.io.BIO;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        while(true){
            Scanner scanner = new Scanner(System.in);

            String s = scanner.next().trim();

            socket.getOutputStream().write(s.getBytes());
        }

//        socket.close();
    }
}
