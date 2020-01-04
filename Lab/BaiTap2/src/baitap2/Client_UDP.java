/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client_UDP {

    public final static int port = 7777;
    public final static String ServerIP = "127.0.0.1";

    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName(ServerIP);
            DatagramSocket ds = new DatagramSocket();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.print("Nhap vao n(so dong): ");
            String n = br.readLine();
            byte[] soDong = n.getBytes();
            DatagramPacket dpN = new DatagramPacket(soDong, soDong.length, ia, port);
            ds.send(dpN);

            System.out.print("Nhap vao m(so cot): ");
            String m = br.readLine();
            byte[] soCot = m.getBytes();
            DatagramPacket dpM = new DatagramPacket(soCot, soCot.length, ia, port);
            ds.send(dpM);
            int N = Integer.parseInt(n);//so dong
            int M = Integer.parseInt(m);//so cot

            String tmp;
            byte[] data = new byte[6000];
            System.out.println("Nhap vao ma tran " + N + " x " + M + ": ");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print("Phan tu[" + i + "][" + j + "]: ");
                    tmp = br.readLine();
                    data = tmp.getBytes();
                    DatagramPacket dpTmp = new DatagramPacket(data, data.length, ia, port);
                    ds.send(dpTmp);
                }
            }

            byte[] data1 = new byte[6000];
            DatagramPacket incoming1 = new DatagramPacket(data1, data1.length);
            ds.receive(incoming1);
            System.out.println(new String(incoming1.getData(), 0, incoming1.getLength()));

            byte[] data2 = new byte[6000];
            DatagramPacket incoming2 = new DatagramPacket(data2, data2.length);
            ds.receive(incoming2);
            System.out.println(new String(incoming2.getData(), 0, incoming2.getLength()));

        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(Client_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
