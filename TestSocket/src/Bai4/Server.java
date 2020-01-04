/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai4;

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
            String str1 = dis.readUTF();
            System.out.println("Nhận tại server: " + str1);

            //Server gửi về client
            System.out.println("Gửi về client: ");
            String str2 = chuanHoa(str1);
            System.out.println(str2);
            dos.writeUTF(str2);

            socket.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String chuanHoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                str += " ";
            }
        }
        return str;
    }

    public static void main(String[] args) {
        Bai4.Server s = new Bai4.Server();
    }
}
