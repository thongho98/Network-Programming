/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author HOQUOCTHONG
 */
public class HandledInfomation extends Thread {

    private MulticastSocket socket;
    private DatagramPacket inPacket;
    private DatagramPacket outPacket;
    private boolean running;

    public HandledInfomation(MulticastSocket socket) {
        try {
            this.socket = socket;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Gửi tin nhắn
    @Override
    public void run() {
        running = true;
        byte[] buf = new byte[2048];
        try {
            while (running) {
                DatagramPacket data1 = new DatagramPacket(buf, buf.length);
                socket.receive(data1);
                String strData1 = new String(data1.getData(), 0, data1.getLength());


//                dataReponse1 = strData1.getBytes();
//                DatagramPacket dpData1 = new DatagramPacket(dataReponse1, dataReponse1.length, data2.getAddress(), data2.getPort());
//                socket.send(dpData1);
//
//                dataReponse2 = strData2.getBytes();
//                DatagramPacket dpData2 = new DatagramPacket(dataReponse2, dataReponse2.length, data1.getAddress(), data1.getPort());
//                socket.send(dpData2);
            }
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
