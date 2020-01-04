package baitap23;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public Server() {
        Scanner scanner = new Scanner(System.in);
        try {
            //Tao Socket server
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server dang goi port: " + ss.getLocalPort());
            Socket socket = ss.accept();
            System.out.println("Đã có client kết nối!");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            
            //Nhận từ client gửi lên server
            //Ví dụ ổ D chứa File Client, E chứa File Server.
            String tenFile = dis.readUTF();
            System.out.println("Nhập tại server tên file: " + tenFile);
            String message = "";
            
            if (uploadFileToServer(tenFile)) {
                message = "Upload file thành công!";
            } else {
                message = "Upload file Lỗi!";
            }
            
            //Server gửi về client
            System.out.println("Gửi về client: ");
            dos.writeUTF(message);

            //Đóng kết nối
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private boolean uploadFileToServer(String tenFile) {
        try {
            FileInputStream fis = new FileInputStream("D:\\" + tenFile);
            byte[] b = new byte[2002];
            fis.read(b, 0, b.length);
            FileOutputStream fos = new FileOutputStream("E:\\" + tenFile);
            fos.write(b, 0, b.length);
            return true;
        } catch (Exception e) {
            System.err.println("Lỗi: " + e);
        }
        return false;
    }
        
    public static void main(String[] args) {
        baitap23.Server server = new baitap23.Server();
    }
}
