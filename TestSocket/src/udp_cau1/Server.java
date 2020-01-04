/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_cau1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server {
    public final static int PORT_SERVER = 7;
    public final static byte[] Buffer = new byte[4048];
    
    
    public static void main(String[] args) {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(PORT_SERVER);
            System.out.println("Server dang doi ket noi: ..");
            DatagramPacket dp = new DatagramPacket(Buffer, PORT_SERVER);
            
            ds.receive(dp);
            System.out.println("Server da nhan duoc du lieu: ");
            String chuoinhan = new String(dp.getData(),0, dp.getLength());
            System.out.println("Chuoi nhan duoc la: "+chuoinhan);
            String chuoichuanhoa = "";
            DatagramPacket outdp = new DatagramPacket(chuoichuanhoa.getBytes(), dp.getLength(),dp.getAddress(),dp.getPort());
            ds.send(outdp);
            System.out.println("Server da gui cho client du lieu "+chuoichuanhoa);
        } catch (Exception e) {
        }
    }
}
