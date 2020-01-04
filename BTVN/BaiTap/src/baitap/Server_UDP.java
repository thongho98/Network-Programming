/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server_UDP {
    
    public final static int port = 7777;
    
    public Server_UDP(){
        
        try {
            DatagramSocket ds = new DatagramSocket(port);
            byte[] request = new byte[6000];
            byte[] reponse = new byte[6000];
            while (true) {                
                System.out.println("Server đang đợi kết nối...");
                
                //Lay yeu cau
                DatagramPacket dataRequest = new DatagramPacket(request, request.length);
                ds.receive(dataRequest);
                String str = new String(dataRequest.getData(), 0, dataRequest.getLength());
                System.out.println("Request: " + str);
                
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void main(String[] args) {
        Server_UDP server = new Server_UDP();
    }
}
