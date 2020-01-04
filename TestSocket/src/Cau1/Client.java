/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author HOQUOCTHONG
 */
public class Client {
    
    public Client(){
        OutputStream os = null;
        InputStream is = null;
        try {
            Socket socket = new Socket("localhost",7777);
            os = socket.getOutputStream();
            is = socket.getInputStream();
            
            //Từ client gửi lên server
            System.out.println("Nhập tại client: ");
            int ch = System.in.read();
            os.write(ch);
            
            //Đọc từ server
            int ch2 = is.read();
            System.out.println("Nhận từ sever về: "+ (char)ch2);
            
            socket.close();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        Client c = new Client();
    }
}
