/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server {

    public Server() {
        OutputStream os = null;
        InputStream is = null;
        try {
            //Tao Socket server
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server dang goi port: "+ ss.getLocalPort());
            Socket socket = ss.accept();
            System.out.println("Đã có client kết nối!");
            os = socket.getOutputStream();
            is = socket.getInputStream();
            
            //Nhan tu client
            int ch = is.read();
            System.out.println("Nhận tại server: " + (char)ch);
            
            //Server gửi về client
            System.out.println("Gửi về client: ");
            int ch2 = System.in.read();
            os.write(ch2);
            
            socket.close();
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
    }
}
