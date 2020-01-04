/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai4;

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
            String str1 = scanner.nextLine();
            dos.writeUTF(str1);

            //Đọc từ server
            String str2 = dis.readUTF();
            System.out.println("Nhận từ sever về: " + str2);

            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Bai4.Client s = new Bai4.Client();
    }
}
