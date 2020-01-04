/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_cau1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.channels.DatagramChannel;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client {
    public final int PORT = 7;
    public final static byte[] Buff = new byte[4048];
    public final static String ServerIP = "127.0.0.1";
    
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            InetAddress add = InetAddress.getByAddress(ServerIP.getBytes());
            System.out.println("Vui long nhap chuoi: ");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String theString = br.readLine();
            byte[] datachuoi = theString.getBytes();
            
            DatagramPacket outchuoi = new DatagramPacket(datachuoi, datachuoi.length);
            ds.send(outchuoi);
            
            DatagramPacket inchuoi = new DatagramPacket(Buff, Buff.length);
            ds.receive(inchuoi);
            String thestring =new String(inchuoi.getData(), 0 ,inchuoi.getLength());
            System.out.println("Chuoi da duoc chuan hoa: "+ theString);
            
        } catch (Exception e) {
        }
    }
}
