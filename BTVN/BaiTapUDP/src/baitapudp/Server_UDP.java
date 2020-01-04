package baitapudp;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Server_UDP {

    public final static int port = 7777;

    public Server_UDP() {

        try {
            DatagramSocket ds = new DatagramSocket(port);
            byte[] request = new byte[6000];
            byte[] reponse = new byte[6000];
            String message = "";
            while (true) {
                System.out.println("Server đang đợi kết nối...");

                //Lay yeu cau
                DatagramPacket dataRequest1 = new DatagramPacket(request, request.length);
                ds.receive(dataRequest1);
                String username = new String(dataRequest1.getData(), 0, dataRequest1.getLength());
                System.out.println("Username: " + username);

                DatagramPacket dataRequest2 = new DatagramPacket(request, request.length);
                ds.receive(dataRequest2);
                String pass = new String(dataRequest2.getData(), 0, dataRequest2.getLength());
                System.out.println("Password: " + pass);

                //Kiem tra dang nhap
                if (checkLogin(username, pass)) {
                    System.out.println("Đăng nhập thành công!");
                    message = "true";
                    reponse = message.getBytes();
                    DatagramPacket dpReponse1 = new DatagramPacket(reponse, reponse.length, dataRequest1.getAddress(), dataRequest1.getPort());
                    ds.send(dpReponse1);   
                } else {
                    System.out.println("Đăng nhập thất bại!");
                    message = "false";
                    reponse = message.getBytes();
                    DatagramPacket dpReponse1 = new DatagramPacket(reponse, reponse.length, dataRequest1.getAddress(), dataRequest1.getPort());
                    ds.send(dpReponse1);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi" + e);
        }
    }

    public boolean checkLogin(String username, String password) {
        File file = new File("D:\\USERPASS.txt");
        Scanner scanner;
        if (file.isFile()) {
            System.out.println("Đọc file thành công");
            try {
                scanner = new Scanner(file);
                String str;
                while (scanner.hasNextLine()) {
                    str = scanner.nextLine();
                    str = str.trim();
                    str = str.replaceAll("\\s+", " ");
                    String temp[] = str.split(" ");
                    if (temp[0].equals(username) && temp[1].equals(password)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e);
            }
        } else {
            System.out.println("Đọc file thất bại!");
        }
        return false;
    }

    public static void main(String[] args) {
        Server_UDP server = new Server_UDP();
    }
}
