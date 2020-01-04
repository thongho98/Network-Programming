/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap;

import java.io.BufferedReader;
import java.io.File;
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
    
    public Client_UDP(){
        
        try {
            InetAddress ia = InetAddress.getByName(ServerIP);
            DatagramSocket ds = new DatagramSocket();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap tên file: ");
            String tenFile = scanner.nextLine();
//            File f = new File(tenFile);

            byte[] request = tenFile.getBytes();
            DatagramPacket dpRequest = new DatagramPacket(request, request.length, ia, port);
            ds.send(dpRequest);
            
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        Client_UDP client = new Client_UDP();
    }
}
