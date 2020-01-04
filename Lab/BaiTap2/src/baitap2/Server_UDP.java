/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap2;

import java.io.IOException;
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
            byte[] n = new byte[6000];
            byte[] m = new byte[6000];
            while (true) {
                System.out.println("Server dang doi ket noi!");
                DatagramPacket incomingN = new DatagramPacket(n, n.length);
                ds.receive(incomingN);
                String strN = new String(incomingN.getData(), 0, incomingN.getLength());

                DatagramPacket incomingM = new DatagramPacket(m, m.length);
                ds.receive(incomingM);
                String strM = new String(incomingM.getData(), 0, incomingM.getLength());
                int N = Integer.parseInt(strN);//so dong
                int M = Integer.parseInt(strM);//so cot

                int mt[][] = new int[N][M];
                byte[] tmp = new byte[6000];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        DatagramPacket dataComing = new DatagramPacket(tmp, tmp.length);
                        ds.receive(dataComing);
                        String dataTmp = new String(dataComing.getData(), 0, dataComing.getLength());
                        mt[i][j] = Integer.parseInt(dataTmp);
                    }
                }
                System.out.println("Mang 2 chieu nhan duoc la: ");
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        System.out.print(mt[i][j] + "   ");
                    }
                    System.out.println();
                }

                int max, second;
                max = mt[0][0];
                second = mt[0][0];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (max < mt[i][j]) {
                            max = mt[i][j];
                        }
                    }
                }
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (max != mt[i][j]) {
                            if (mt[i][j] > second) {
                                second = mt[i][j];
                            }
                        }
                    }
                }
                String outData1 = "So lon nhat la: " + max + " nam o vi tri thu ";
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (max == mt[i][j]) {
                            outData1 += "[" + i + "][" + j + "] ";
                        }

                    }
                }
                byte[] data1 = outData1.getBytes();
                DatagramPacket dp1 = new DatagramPacket(data1, data1.length, incomingN.getAddress(), incomingN.getPort());
                ds.send(dp1);

                String outData2 = "So lon  nhi la: " + second + " nam o vi tri thu ";
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (second == mt[i][j]) {
                            outData2 += "[" + i + "][" + j + "] ";
                        }

                    }
                }

                byte[] data2 = outData2.getBytes();
                DatagramPacket dp2 = new DatagramPacket(data2, data2.length, incomingN.getAddress(), incomingN.getPort());
                ds.send(dp2);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
