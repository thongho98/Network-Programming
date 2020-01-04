/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai17;

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
            System.out.println("Nhập tại client số nguyên n: ");
            int n = scanner.nextInt();
            dos.writeInt(n);

            //Đọc từ server
            System.out.println("Nhận từ sever về: ");
            String str = dis.readUTF();
            String[] temp = str.split("-");
            for (String element : temp) {
                System.out.print(element + "\t");
            }
            
            //Đóng kết nối
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        bai17.Client client = new bai17.Client();
    }
}
