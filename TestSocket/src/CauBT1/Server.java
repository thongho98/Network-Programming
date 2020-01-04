/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CauBT1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server {

    public Server() {
        PrintStream ps = null;
        try {
            //Tao Socket server
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server dang goi port: " + ss.getLocalPort());
            Socket socket = ss.accept();
            System.out.println("Đã có client kết nối!");
            
            Scanner in = new Scanner(socket.getInputStream());
            PrintStream out = new PrintStream(socket.getOutputStream());
            
            //Nhan tu client
            String s = in.nextLine();
            String[] chuoi = s.split("/");
            for (String str : chuoi) {
                System.out.println(str);
            }
            System.out.println("Nhận tại server: ");
            int thang = Integer.parseInt(chuoi[0]);
            int nam = Integer.parseInt(chuoi[1]);
            
            //Server gửi về client
            System.out.println("Gửi về client: ");
            int day = xuatngay(thang, nam);
            System.out.println("Ngay: "+ day);
            out.println(day);
            socket.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public boolean CheckYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 4 == 0 && year % 100 != 0) {

            return true;
        }
        return false;
    }

    public int xuatngay(int thang, int nam) {
        int day = 0;
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
            case 2: {
                if (CheckYear(nam)) {
                    day = 29;
                } else {
                    day = 28;
                }
            }
        }
        return day;
    }

    public static void main(String[] args) {
        Server sever = new Server();
    }
}
