/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server {

    public Server() {
        Scanner scanner = new Scanner(System.in);
        try {
            //Tao Socket server
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server dang goi port: " + ss.getLocalPort());
            Socket socket = ss.accept();
            System.out.println("Đã có client kết nối!");
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            //Nhận từ client gửi lên server
            String urlFile = dis.readUTF();
            System.out.println("Nhập tại server tên file: " + urlFile);
            String message = "";
            File file = new File(urlFile);
            if (file.isFile()) {
                moveFile(urlFile);
                message = "Di chuyển thành công!";
            } else {
                message = "File không tồn tại!";
            }

            //Server gửi về client
            System.out.println("Gửi về client: " + message);
            dos.writeUTF(message);

            //Đóng kết nối
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void moveFile(String urlFile) {
        try {
            File file = new File(urlFile);
            FileInputStream fis = new FileInputStream(urlFile);
            byte[] b = new byte[2002];
            fis.read(b, 0, b.length);
            FileOutputStream fos = new FileOutputStream("E://text2.txt");
            fos.write(b, 0, b.length);
            fis.close();
            file.delete();
        } catch (Exception e) {
            System.err.println("Lỗi: " + e);
        }
    }

    public static void main(String[] args) {
        baitap1.Server s = new baitap1.Server();
    }
}
/*

 */
