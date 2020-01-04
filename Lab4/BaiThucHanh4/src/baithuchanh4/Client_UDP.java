/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baithuchanh4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client_UDP {

    public final static int port = 7777;
    public final static String ServerIP = "127.0.0.1";

    public Client_UDP() {
        try {
            InetAddress ia = InetAddress.getByName(ServerIP);
            DatagramSocket ds = new DatagramSocket();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            //Menu
            System.out.println("--------MENU ----------");
            System.out.println("1. Tao bang trong database (Laptrinhmang).");
            System.out.println("2. Nhap du lieu vao bang can chon.");
            //Nhap lua chon
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Nhap lua chon cua ban:");
                int chon = scanner.nextInt();
                scanner.nextLine();
                switch (chon) {
                    case 1: {
                        String str = "";
                        System.out.print("Nhập tên bảng: ");
                        String tableName = scanner.nextLine();
                        str += tableName + '-';
                        System.out.print("Nhập số cột: ");
                        int nColumn = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < nColumn; i++) {
                            System.out.print("Tên trường thứ " + (i + 1) + ": ");
                            String column = scanner.nextLine();
                            str += column + '-';
                            System.out.print("Kiểu dữ liệu trường thứ " + (i + 1) + ": ");
                            String typecolumn = scanner.nextLine();
                            str += typecolumn + '-';
                        }

                        byte[] request1 = str.getBytes();
                        DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
                        ds.send(dpRequest1);

                        byte[] reponse = new byte[6000];
                        DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
                        ds.receive(dpReponse);
                        String strReponse = new String(dpReponse.getData(), 0, dpReponse.getLength());
                        System.out.println(strReponse);
                        break;
                    }
                    
                    //Chưa hoàn thiện
                    case 2: {
                        String str = "";
                        System.out.print("Nhập tên bảng: ");
                        String tableName = scanner.nextLine();
                        for (int i = 0; i < 5; i++) {
                            System.out.print("Nhập record thứ " + (i + 1) + ": ");
                            String column = scanner.nextLine();
                            str += column + '-';
                        }
                        System.out.println(str);
                        byte[] request1 = str.getBytes();
                        DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
                        ds.send(dpRequest1);
                        
                        byte[] reponse = new byte[6000];
                        DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
                        ds.receive(dpReponse);
                        String strReponse = new String(dpReponse.getData(), 0, dpReponse.getLength());
                        System.out.println(strReponse);
                        break;
                    }
                    default: {
                        System.out.println("Vui long nhập lại");
                    }
                }
            } while (true);

        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
    }

    public static void main(String[] args) {
        Client_UDP c = new Client_UDP();
    }
}
