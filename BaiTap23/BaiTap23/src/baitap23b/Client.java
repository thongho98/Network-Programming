package baitap23b;

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
            System.out.println("Client bắt đầu chạy...");
            //Nhận từ client gửi lên server
            //Ví dụ ổ D chứa File Client, E chứa File Server.
            String tenFileServer = dis.readUTF();
            System.out.println("Nhận tại client tên file: " + tenFileServer);
            
            String message = "";
            if (uploadFileToClient(tenFileServer)) {
                message = "Upload file đến client thành công!";
            } else {
                message = "Upload file đến client thất bại!";
            }

            //Gửi lên server
            System.out.println("Gửi lên server: "+message);
            dos.writeUTF(message);
            
            //Đóng kết nối
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private boolean uploadFileToClient(String tenFile) {
        try {
            FileInputStream fis = new FileInputStream("E:\\" + tenFile);
            byte[] b = new byte[2002];
            fis.read(b, 0, b.length);
            FileOutputStream fos = new FileOutputStream("D:\\" + tenFile);
            fos.write(b, 0, b.length);
            return true;
        } catch (Exception e) {
            System.err.println("Lỗi: " + e);
        }
        return false;
    }
    
    public static void main(String[] args) {
        baitap23b.Client client = new baitap23b.Client();
    }
}
