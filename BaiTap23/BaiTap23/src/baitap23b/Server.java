package baitap23b;

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
            
            //Từ client gửi lên server
            System.out.println("Nhập tại server tên file: ");
            String tenFileServer = scanner.nextLine();
            dos.writeUTF(tenFileServer);
            
            //Server gửi về client
            String message = dis.readUTF();
            System.out.println("Client trả về: "+message);

            //Đóng kết nối
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
        
    public static void main(String[] args) {
        baitap23b.Server server = new baitap23b.Server();
    }
}
