/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai17;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server {

    public Server() {
        try {
            //Tao Socket server
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server dang goi port: " + ss.getLocalPort());
            Socket socket = ss.accept();
            System.out.println("Đã có client kết nối!");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            //Nhan tu client
            int n = dis.readInt();
            System.out.println("Nhận số n tại server: " + n);

            //Server gửi về client
            System.out.println("Gửi về client: ");
            List<Integer> list = dayFibonaci(n);
            String str = "";
            for (Integer i : list) {
                System.out.print(i + "\t");
                str += i + "-";
            }
            dos.writeUTF(str);
            
            //Đóng kết nối
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static List<Integer> dayFibonaci(int n) {
        int i = 0;
        List<Integer> listNumber = new ArrayList<Integer>();
        while (tinhFibonaci(i) < n) {
            int soFibo = tinhFibonaci(i);
            if (isSoNguyenTo(soFibo)) {
                listNumber.add(soFibo);
            }
            i++;
        }
        return listNumber;
    }

    private static int tinhFibonaci(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return n;
        }
        return tinhFibonaci(n - 1) + tinhFibonaci(n - 2);
    }

    private static boolean isSoNguyenTo(int n) {
        if (n < 2) {
            return false;
        }
        int range = (int) Math.sqrt(n);
        for (int i = 2; i <= range; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        bai17.Server server = new bai17.Server();
    }
}
