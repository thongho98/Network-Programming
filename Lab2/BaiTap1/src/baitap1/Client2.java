/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client2 {

    Scanner scanner = new Scanner(System.in);

    public Client2() {
        try {
            Socket socket = new Socket("localhost", 7);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            while (true) {
                System.out.println("Nhập client 2: ");
                String str1 = scanner.nextLine();
                if (checkPath(str1)) {
                    String content = readFile(str1);
                    dos.writeUTF(content);
                } else {
                    dos.writeUTF(str1);
                }

                String str2 = dis.readUTF();
                System.out.println("Nhận client 1: " + str2);
                writeFile(str2);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private boolean checkPath(String path) {
        String pattern = "^.*(\\\\).*$";
        if (path.matches(pattern)) {
            return true;
        }
        return false;
    }

    private String readFile(String tenFile) {
        Scanner scan;
        String temp, data = "";
        try {
            scan = new Scanner(new File(tenFile));

            while (scan.hasNextLine()) {
                temp = scan.nextLine();
                data += temp;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e);
        }
        return data;
    }

    private boolean writeFile(String content) {
        try {
            FileOutputStream fos = new FileOutputStream("D:\\Fileluu.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes(content);
            System.out.println("Lưu file vào đường dẫn D:\\Fileluu.txt thành công!");
            fos.close();
            dos.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static void main(String[] args) {
        Client2 c2 = new Client2();
    }
}
