/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client2_UDP {

    public final static int port = 7777;
    public final static String ServerIP = "224.0.0.1";

    public Client2_UDP() {
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress ia = InetAddress.getByName(ServerIP);
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            while (true) {
                System.out.println("Client 2: ");
                String chon = br.readLine();
                byte[] request = chon.getBytes();
                DatagramPacket dpRequest = new DatagramPacket(request, request.length, ia, port);
                ds.send(dpRequest);
                
                //Reponse
                byte[] reponse = new byte[6000];
                DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
                ds.receive(dpReponse);
                System.out.println("Client 1 trả lời:");
                System.out.println(new String(dpReponse.getData(), 0, dpReponse.getLength()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Client2_UDP client = new Client2_UDP();
    }
}
