/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName(ServerIP);
            DatagramSocket ds = new DatagramSocket();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap tên file: ");
            String tenFile = scanner.nextLine();
            File f = new File(tenFile);
            if (f.isFile()) {
                FileReader fr = new FileReader(f);
                BufferedReader brFile = new BufferedReader(fr);
                String[] line = brFile.readLine().split(",");
                for (String str : line) {
                    System.out.println(str);
                }

                //Lua chon yeu cau
                System.out.println("Nhập lựa chọn của bạn: ");
                String chon = br.readLine();
                byte[] request = chon.getBytes();
                DatagramPacket dpRequest = new DatagramPacket(request, request.length, ia, port);
                ds.send(dpRequest);

                switch (chon) {
                    case "1": {
                        String dataRequest1 = line[3];
                        byte[] request1 = dataRequest1.getBytes();
                        DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
                        ds.send(dpRequest1);
                        break;
                    }
                    case "2": {
                        String dataRequest1 = line[4];
                        byte[] request2 = dataRequest1.getBytes();
                        DatagramPacket dpRequest2 = new DatagramPacket(request2, request2.length, ia, port);
                        ds.send(dpRequest2);
                        break;
                    }
                    case "3": {
                        String dataRequest3 = line[5];
                        byte[] request3 = dataRequest3.getBytes();
                        DatagramPacket dpRequest3 = new DatagramPacket(request3, request3.length, ia, port);
                        ds.send(dpRequest3);
                        break;
                    }
                }

                //Tra ve data khi xu li
                byte[] data = new byte[6000];
                DatagramPacket incoming = new DatagramPacket(data, data.length);
                ds.receive(incoming);
                System.out.println("Sau khi xử lí:");
                System.out.println(new String(incoming.getData(), 0, incoming.getLength()));
            } else {
                System.out.println("Không tìm thấy file");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
