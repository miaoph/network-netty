package com.miaoph.cn.networknetty.bio;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(10080));
            System.out.println("通信监听开始========>>>>>>>");
            for (; ; ) {
                serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static class serverTask implements Runnable {
        private Socket socket = null;

        public serverTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

//            try (
//                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
//                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//                     inputStream.readUTF();
//
//                    outputStream.writeUTF();
//
//            ) {
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

        }
    }
}
