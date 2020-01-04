/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tracuutt;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                if (str.length() > 5) {
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
                } else {
                    String kq = findStudent(str);
                    System.out.println(kq);
                    reponse = kq.getBytes();
                    DatagramPacket dpReponse1 = new DatagramPacket(reponse, reponse.length, dataRequest1.getAddress(), dataRequest1.getPort());
                    ds.send(dpReponse1);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
    }

    public boolean createTable(String str) {
        try {
            Connection cnn = MyConnection.getConnection();
            PreparedStatement pstm = cnn.prepareStatement(str);
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
        return false;
    }

    public String findStudent(String tenSV) {
        String sql = "SELECT sv.MaSV, sv.HoSV, sv.TenSV, mh.TenMH, kq.Diem, kq.LanThi \n"
                + "FROM dbo.KetQua AS kq, dbo.SinhVien AS sv, dbo.DMMH AS mh\n"
                + "WHERE kq.MaMH = mh.MaMH AND kq.MaSV = sv.MaSV AND sv.TenSV LIKE N'%" + tenSV + "%'";
        String kq = "";
        try {
            Connection cnn = MyConnection.getConnection();
            PreparedStatement pstm = cnn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String masv = rs.getString("MaSV");
                kq += masv + '-';
                String ho = rs.getString("HoSV");
                kq += ho + '-';
                String ten = rs.getString("TenSV");
                kq += ten + '-';
                String lanthi = rs.getString("LanThi");
                kq += lanthi + '-';
                String monhoc = rs.getString("TenMH");
                kq += monhoc + '-';
                String diem = rs.getString("Diem");
                kq += diem + ' ';
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
        return kq;
    }

    public static void main(String[] arg) {
        Server_UDP s = new Server_UDP();
    }
}
