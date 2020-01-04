package baitapcsdl;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                String username = new String(dataRequest1.getData(), 0, dataRequest1.getLength());
                System.out.println("Username: " + username);

                DatagramPacket dataRequest2 = new DatagramPacket(request, request.length);
                ds.receive(dataRequest2);
                String pass = new String(dataRequest2.getData(), 0, dataRequest2.getLength());
                System.out.println("Password: " + pass);

                //Kiem tra dang nhap
                if (checkLogin(username, pass)) {
                    System.out.println("Đăng nhập thành công!");
                    message = "true";
                    reponse = message.getBytes();
                    DatagramPacket dpReponse1 = new DatagramPacket(reponse, reponse.length, dataRequest1.getAddress(), dataRequest1.getPort());
                    ds.send(dpReponse1);
                    
                    
                } else {
                    System.out.println("Đăng nhập thất bại!");
                    message = "false";
                    reponse = message.getBytes();
                    DatagramPacket dpReponse1 = new DatagramPacket(reponse, reponse.length, dataRequest1.getAddress(), dataRequest1.getPort());
                    ds.send(dpReponse1);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi" + e);
        }
    }

    public boolean checkLogin(String username, String password) {
        String sql = "SELECT COUNT(*) FROM dbo.SINHVIEN WHERE UserName = N'"+username+"' AND PassWord = N'"+password+"'";
        try {
            Connection cnn = MyConnection.getConnection();
            PreparedStatement pstm = cnn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int kq = rs.getInt(1);
                if(kq == 1) return true;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
        return false;
    }
    
    public String getBoDe() {
        String sql = "SELECT TOP(10) * FROM dbo.BODE";
        try {
            Connection cnn = MyConnection.getConnection();
            PreparedStatement pstm = cnn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            String kq ="";
            while (rs.next()) {
                String cauhoi = rs.getString("CAUHOI");
                kq += cauhoi + '-';
                String trinhdo = rs.getString("TRINHDO");
                kq += trinhdo + '-';
                String noidung = rs.getString("NOIDUNG");
                kq += noidung + '-';
                String dapanA = rs.getString("A");
                kq += dapanA + '-';
                String dapanB = rs.getString("B");
                kq += dapanB + '-';
                String dapanC = rs.getString("C");
                kq += dapanC + '-';
                String dapanD = rs.getString("D");
                kq += dapanD + '-';
                String dapan = rs.getString("DAP_AN");
                kq += dapan + ' ';
            }
            return kq;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        Server_UDP server = new Server_UDP();
    }
}
