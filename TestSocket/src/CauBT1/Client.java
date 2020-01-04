/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CauBT1;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client {

    public Client() {
        PrintStream ps = null;
        try {
            Socket socket = new Socket("localhost", 7777);
            Scanner in = new Scanner(socket.getInputStream());
            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner nhap = new Scanner(System.in);
            
            //Từ client gửi lên server
            System.out.println("Nhập tại client: ");
            System.out.println("Nhập thang: ");
            int thang = nhap.nextInt();
            System.out.println("Nhập nam: ");
            int nam = nhap.nextInt();
            
            String s = thang+"/"+nam;
            out.print(s);
            
            
            //Đọc từ server
            System.out.println("Nhận từ sever về: ");
            int ngay = in.nextInt();
            System.out.println("Thang " + thang + " Nam " + nam + " co " + ngay + " ngay");
            socket.close();
        } catch (Exception e) {
        }
    }

    public static void main(String args[]) {
        Client c = new Client();
    }
}
