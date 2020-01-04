/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tracuutt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client_UDP {

    public final static int port = 7777;
    public final static String ServerIP = "127.0.0.1";

    public Client_UDP() {
        try {
            InetAddress ia = InetAddress.getByName(ServerIP);
            DatagramSocket ds = new DatagramSocket();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            //Menu
            System.out.println("--------MENU ----------");
            System.out.println("1. Tạo bảng SinhVien trong database (Laptrinhmang).");
            System.out.println("2. Tạo bảng DMMH trong database (Laptrinhmang).");
            System.out.println("3. Tạo bảng KetQua trong database (Laptrinhmang).");
            System.out.println("4. Tra cứu thông tin trong database (Laptrinhmang).");
            //Nhap lua chon
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Nhập yêu cầu của bạn:");
                int chon = scanner.nextInt();
                scanner.nextLine();
                switch (chon) {
                    case 1: {
                        String str = "CREATE TABLE SinhVien(\n"
                                + "	MaSV CHAR(3) NOT NULL PRIMARY KEY,\n"
                                + "	HoSV NVARCHAR(15) NOT NULL,\n"
                                + "	TenSV NVARCHAR(7) NOT NULL,\n"
                                + "	Phai NCHAR(7),\n"
                                + "	NgaySinh DATETIME NOT NULL,\n"
                                + "	NoiSinh NVARCHAR(20),\n"
                                + "	MaKhoa CHAR(2),\n"
                                + "	HocBong FLOAT\n"
                                + ")";

                        byte[] request1 = str.getBytes();
                        DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
                        ds.send(dpRequest1);

                        byte[] reponse = new byte[6000];
                        DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
                        ds.receive(dpReponse);
                        String strReponse = new String(dpReponse.getData(), 0, dpReponse.getLength());
                        System.out.println(strReponse);
                        break;
                    }
                    case 2: {
                        String str = "CREATE TABLE DMMH(\n"
                                + "	MaMH CHAR(2) NOT NULL PRIMARY KEY,\n"
                                + "	TenMH NVARCHAR(25) NOT NULL,\n"
                                + "	SoTiet TINYINT \n"
                                + ")";

                        byte[] request1 = str.getBytes();
                        DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
                        ds.send(dpRequest1);

                        byte[] reponse = new byte[6000];
                        DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
                        ds.receive(dpReponse);
                        String strReponse = new String(dpReponse.getData(), 0, dpReponse.getLength());
                        System.out.println(strReponse);
                        break;
                    }
                    case 3: {
                        String str = "CREATE TABLE KetQua(\n"
                                + "	MaSV CHAR(3) NOT NULL,\n"
                                + "	MaMH CHAR(2) NOT NULL,\n"
                                + "	LanThi TINYINT,\n"
                                + "	Diem DECIMAL(4,2),\n"
                                + "	CONSTRAINT KetQua_MaSV_MaMH_LanThi_pk PRIMARY KEY (MaSV,MaMH,LanThi)\n"
                                + ")";

                        byte[] request1 = str.getBytes();
                        DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
                        ds.send(dpRequest1);

                        byte[] reponse = new byte[6000];
                        DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
                        ds.receive(dpReponse);
                        String strReponse = new String(dpReponse.getData(), 0, dpReponse.getLength());
                        System.out.println(strReponse);
                        break;
                    }
                    case 4: {
                        System.out.println("Nhập tên sinh viên cần tra cứu: ");
                        String tenSV = scanner.nextLine();

                        byte[] request1 = tenSV.getBytes();
                        DatagramPacket dpRequest1 = new DatagramPacket(request1, request1.length, ia, port);
                        ds.send(dpRequest1);

                        byte[] reponse = new byte[6000];
                        DatagramPacket dpReponse = new DatagramPacket(reponse, reponse.length);
                        ds.receive(dpReponse);
                        String strReponse = new String(dpReponse.getData(), 0, dpReponse.getLength());
                        System.out.println("Thông tin sinh viên tìm kiếm:");
                        System.out.println("Mã SV \t Họ \t Tên \t Số lần thi \t Môn học \t Điểm");
                        String[] temp = strReponse.split("-");
                        System.out.println(temp[0] + "\t" + temp[1] + "\t" + temp[2] + "\t\t" + temp[3] + "\t" + temp[4] + "\t" + temp[5]);
                        break;
                    }
                    default: {
                        System.out.println("Yêu cầu bạn không hợp lệ! Vui lòng nhập lại");
                    }
                }
            } while (true);

        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
    }

    public static void main(String[] args) {
        Client_UDP c = new Client_UDP();
    }
}
