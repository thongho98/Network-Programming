/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap;

import static baitap.Client_UDP.port;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server_UDP {

    public final static int port = 7777;

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(port);
            byte[] number = new byte[6000];
            byte[] dataRequest = new byte[6000];
            byte[] dataReponse = new byte[6000];
            while (true) {
                System.out.println("Server dang doi ket noi!");

                //Lay yeu cau
                DatagramPacket numberRequest = new DatagramPacket(number, number.length);
                ds.receive(numberRequest);
                String numberString = new String(numberRequest.getData(), 0, numberRequest.getLength());
                int choose = Integer.parseInt(numberString);

                //Lay chuoi cat
                DatagramPacket dataRequest1 = new DatagramPacket(dataRequest, dataRequest.length);
                ds.receive(dataRequest1);
                String data = new String(dataRequest1.getData(), 0, dataRequest1.getLength());

                String message = "";
                switch (choose) {
                    case 1: {
                        int data1 = Integer.parseInt(data);
                        message = tongCacSoNguyenTo(data1);
                        dataReponse = message.getBytes();
                        DatagramPacket dpData = new DatagramPacket(dataReponse, dataReponse.length, numberRequest.getAddress(), numberRequest.getPort());
                        ds.send(dpData);
                        break;
                    }
                    case 2: {
                        message = chuanHoa(data.replaceAll("\"", " "));
                        dataReponse = message.getBytes();
                        DatagramPacket dpData = new DatagramPacket(dataReponse, dataReponse.length, numberRequest.getAddress(), numberRequest.getPort());
                        ds.send(dpData);
                        break;
                    }
                    case 3: {

                        break;
                    }
                    default: {
                        message = "Lựa chọn không phù hợp!";
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static String chuanHoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                str += " ";
            }
        }
        return str;
    }

    private static boolean isSoNguyenTo(int number) {
        if (number < 2) {
            return false;
        }
        int range = (int) Math.sqrt(number);
        for (int i = 2; i <= range; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static String tongCacSoNguyenTo(int so) {
        int sum = 0;
        for (int i = 0; i <= so; i++) {
            if (isSoNguyenTo(i)) {
                sum += i;
            }
        }
        String sumString = String.valueOf(sum);
        return sumString;
    }
}
