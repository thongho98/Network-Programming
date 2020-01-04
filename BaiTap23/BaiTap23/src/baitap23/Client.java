package baitap23;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public Client(){
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 7777);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            
            //Từ client gửi lên server
            System.out.println("Nhập tại client tên file: ");
            String tenFile = scanner.nextLine();
            dos.writeUTF(tenFile);

            //Đọc từ server
            String message = dis.readUTF();
            System.out.println("Nhận từ sever về: "+message);
            
            //Đóng kết nối
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        baitap23.Client client = new baitap23.Client();
    }
}
