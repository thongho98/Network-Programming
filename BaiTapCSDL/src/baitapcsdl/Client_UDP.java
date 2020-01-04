package baitapcsdl;

import java.io.BufferedReader;
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

            while (true) {
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
                    
                } else {
                    System.out.println("Tài khoản không đúng! Vui lòng nhập lại!");
                }
            }
        } catch (Exception e) {
            System.err.println(e); 
        }

    }
    
    public static void main(String[] args) {
        Client_UDP client = new Client_UDP();
    }
}
