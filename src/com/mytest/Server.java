package com.mytest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws IOException {
        System.out.println("服务端正在启动");
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务端已经准备就绪");
        Socket socket = serverSocket.accept();
        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

        DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));
        System.out.println(socket.getLocalSocketAddress());
        System.out.println(socket.getInetAddress());
        System.out.println(socket.getPort());
        System.out.println(socket.getRemoteSocketAddress());
        do {

            double length = dis.readDouble();
            System.out.println("服务器端收到的边长数据为：" + length);
            double result = length * length;
            dos.writeDouble(result);
            dos.flush();

        } while (dis.readInt() != 0);

        socket.close();
        serverSocket.close();

    }

}
         