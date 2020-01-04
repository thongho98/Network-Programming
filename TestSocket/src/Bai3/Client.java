/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client {

    public Client() {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 7777);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            //Từ client gửi lên server
            System.out.println("Nhập tại client: ");
            int ch = scanner.nextInt();
            dos.writeInt(ch);

            //Đọc từ server
            int ch2 = dis.readInt();
            System.out.println("Nhận từ sever về: " + ch2);

            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Bai3.Client s = new Bai3.Client();
    }
}
