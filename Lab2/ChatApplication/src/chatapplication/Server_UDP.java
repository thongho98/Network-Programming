/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server_UDP {

    public static final byte[] BUFFER = new byte[4096];
    public final static int port = 7777;
    public static List<MulticastSocket> listSocket;
    public Server_UDP() {
        MulticastSocket socket = null;
        byte[] dataRequest = new byte[6000];
        byte[] dataReponse1 = new byte[6000];
        byte[] dataReponse2 = new byte[6000];
        
        
//        DatagramPacket inPacket = null;
        try {
            InetAddress ia = InetAddress.getByName(Client1_UDP.ServerIP);
            socket = new MulticastSocket(Client1_UDP.port);
            socket.joinGroup(ia);
            
            while (true) {
                System.out.println("Server đang đợi kết nối...");
                listSocket = new ArrayList<>();
                listSocket.add(socket);
                for (MulticastSocket multicastSocket : listSocket) {
                    System.out.println(multicastSocket.getPort());
                }
                DatagramPacket data1 = new DatagramPacket(dataRequest, dataRequest.length);
                socket.receive(data1);
                String strData1 = new String(data1.getData(), 0, data1.getLength());
                System.out.println("Client 1: "+strData1);
                
                DatagramPacket data2 = new DatagramPacket(dataRequest, dataRequest.length);
                socket.receive(data2);
                String strData2 = new String(data2.getData(), 0, data2.getLength());
                System.out.println("Client 2: "+strData2);
                
                dataReponse1 = strData1.getBytes();
                DatagramPacket dpData1 = new DatagramPacket(dataReponse1, dataReponse1.length, data2.getAddress(), data2.getPort());
                socket.send(dpData1);
                
                dataReponse2 = strData2.getBytes();
                DatagramPacket dpData2 = new DatagramPacket(dataReponse2, dataReponse2.length, data1.getAddress(), data1.getPort());
                socket.send(dpData2);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Server_UDP server = new Server_UDP();
    }
}
