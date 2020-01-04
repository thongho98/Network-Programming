/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baithuchanh4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author HOQUOCTHONG
 */
public class Server_UDP {

    public final static int port = 7777;

    public Server_UDP() {
        try {
            DatagramSocket ds = new DatagramSocket(port);
            byte[] request = new byte[6000];
            byte[] reponse = new byte[6000];
            String message = "";
            while (true) {
                System.out.println("Server đang đợi kết nối...");

                //Lay yeu cau
                DatagramPacket dataRequest1 = new DatagramPacket(request, request.length);
                ds.receive(dataRequest1);
                String str = new String(dataRequest1.getData(), 0, dataRequest1.getLength());
                System.out.println("Chuoi nhan tu client: " + str);

                //Xu lí
                boolean check = createTable(str);
                if (check) {
                    //Gui ve client
                    message = "Tạo bảng thành công!";
                    reponse = message.getBytes();
                    DatagramPacket dpReponse1 = new DatagramPacket(reponse, reponse.length, dataRequest1.getAddress(), dataRequest1.getPort());
                    ds.send(dpReponse1);
                } else {
                    message = "Tạo bảng thất bại!";
                    reponse = message.getBytes();
                    DatagramPacket dpReponse1 = new DatagramPacket(reponse, reponse.length, dataRequest1.getAddress(), dataRequest1.getPort());
                    ds.send(dpReponse1);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
    }

    public boolean createTable(String str) {
        String[] temp = str.split("-");
        try {
            String sql = "CREATE TABLE " + temp[0] + "(\n";
            for (int i = 1; i < temp.length - 1; i += 2) {
                sql += temp[i] + " " + temp[i + 1] + ",\n";
            }
            sql += ")";
            System.out.println(sql);
            Connection cnn = MyConnection.getConnection();
            PreparedStatement pstm = cnn.prepareStatement(sql);
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
        return false;
    }

    public boolean insertData(String str) {
        String[] temp = str.split("-");
        try {
            String sql = "INSERT INTO " + temp[0] + " VALUES (";
            for (int i = 1; i < temp.length - 1; i++) {
                sql += temp[i] + ",";
            }
            sql += ")";
            System.out.println(sql);
        } catch (Exception e) {
        }

        return false;
    }

    public static void main(String[] arg) {
        Server_UDP s = new Server_UDP();
    }
}
