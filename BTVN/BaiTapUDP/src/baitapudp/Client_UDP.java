package baitapudp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_UDP {

    public final static int port = 7777;
    public final static String ServerIP = "127.0.0.1";

    public Client_UDP() {

        try {
            InetAddress ia = InetAddress.getByName(ServerIP);
            DatagramSocket ds = new DatagramSocket();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            //Nhap username
            Scanner scanner = new Scanner(System.in);
            System.out.print("Username: ");
            String username = scanner.nextLine();

            byte[] request1 = username.getBytes();
            DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
            ds.send(dpRequest1);

            //Nhap password
            System.out.print("Password: ");
            String pass = scanner.nextLine();

            byte[] request2 = pass.getBytes();
            DatagramPacket dpRequest2 = new DatagramPacket(request2, request2.length, ia, port);
            ds.send(dpRequest2);

            //Reponse
            byte[] reponse = new byte[6000];
            DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
            ds.receive(dpReponse);
            String str = new String(dpReponse.getData(), 0, dpReponse.getLength());
            boolean checkReponse = Boolean.valueOf(str);
            if (checkReponse) {
                System.out.println("Đăng nhập thành công!");
                System.out.print("Nhập đường dẫn file: ");
                String tenFile = scanner.nextLine();
                File f = new File(tenFile);
                if (f.isFile()) {
                    System.out.println("Đọc File thành công");
                    String data = readFile(tenFile);
                    byte[] request3 = data.getBytes();
                    DatagramPacket dpRequest3 = new DatagramPacket(request3, request3.length, ia, port);
                    ds.send(dpRequest3);
                } else {
                    System.out.println("File không tồn tại");
                }
            } else {
                System.out.println("Bạn đã nhập sai username và password!");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private String readFile(String tenFile) {
        Scanner scan;
        String temp, data = "";
        try {
            scan = new Scanner(new File(tenFile));

            while (scan.hasNextLine()) {
                temp = scan.nextLine();
                temp = temp.trim();
                temp = temp.replaceAll("\\s+", " ");
                String tempArr[] = temp.split(" ");
                for (int i = 0; i < 5; i++) {
                    data += tempArr[i] + " ";
                }
            }
            data = data.trim();
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e);
        }
        return data;
    }

    public static void main(String[] args) {
        Client_UDP client = new Client_UDP();
    }
}
