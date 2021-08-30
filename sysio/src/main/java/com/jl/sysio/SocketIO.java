package com.jl.sysio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketIO {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090, 20);

        System.out.println("step1: new ServerSocket(9090) ");

        while (true) {
            Socket client = server.accept();
            System.out.println("step2: client \t" + client.getPort());

            new Thread(new Runnable() {

                @Override
                public void run() {
                    InputStream in = null;
                    try {
                        in = client.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        while (true) {
                            String dataline = reader.readLine();
                            if (dataline != null) {
                                System.out.println(dataline);
                            } else {
                                client.close();
                            }
                        }
                    } catch (Exception ignore) {

                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException ignore2) {
                            }
                        }
                    }
                }
            });
        }
    }

}
