/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client {
    
    public Client(){
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 7777);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            
            //Từ client gửi lên server
            System.out.println("Nhập tại client đường dẫn:(D:\\");
            String url = scanner.nextLine();
            System.out.println("Nhập tại client đường dẫn ,tên file: (text1.txt)");
            String tenFile = scanner.nextLine();
            
            String urlFile = url+tenFile;
            dos.writeUTF(urlFile);
            dos.writeUTF(tenFile);
            //Đọc từ server
            String message = dis.readUTF();
            System.out.println("Nhận từ sever về: "+message);
            
            //Đóng kết nối
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        baitap1.Client c = new baitap1.Client();
    }
}
