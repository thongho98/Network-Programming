/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server {

    public Server() {
        try {
            //Tao Socket server
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server dang goi port: " + ss.getLocalPort());
            Socket socket = ss.accept();
            System.out.println("Đã có client kết nối!");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            //Nhan tu client
            int ch = dis.readInt();
            System.out.println("Nhận tại server: " + ch);

            //Server gửi về client
            System.out.println("Gửi về client: ");
            int ch2 = tong(ch);
            System.out.println(ch2);
            dos.writeInt(ch2);

            socket.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public int tong(int n) {
        int sum = 0;
        while (n > 0) {
            int so = n % 10;
            n = n / 10;
            sum += so;
        }
        return sum;
    }

    public static void main(String[] args) {
        Bai3.Server s = new Bai3.Server();
    }
}
