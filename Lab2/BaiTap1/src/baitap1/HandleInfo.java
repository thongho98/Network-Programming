/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOQUOCTHONG
 */
public class HandleInfo extends Thread {

    private Socket socket1;
    private Socket socket2;
    
    public HandleInfo(Socket socket1,Socket socket2) {
        this.socket1 = socket1;
        this.socket2 = socket2;
    }

    @Override
    public void run() {
        System.out.println("Processing: " + socket1);
        System.out.println("Processing: " + socket2);
        try {
            DataOutputStream dos1 = new DataOutputStream(socket1.getOutputStream());
            DataInputStream dis1 = new DataInputStream(socket1.getInputStream());
            DataOutputStream dos2 = new DataOutputStream(socket2.getOutputStream());
            DataInputStream dis2 = new DataInputStream(socket2.getInputStream());
            while (true) {
                String str1 = dis1.readUTF(); // Receive data from client 1
                System.out.println("Client 1: "+str1);
                dos2.writeUTF(str1); // Send the results to client 
                
                String str2 = dis2.readUTF(); // Receive data from client
                System.out.println("Client 2: "+str2);
                dos1.writeUTF(str2); // Send the results to client
            }
        } catch (IOException e) {
            System.err.println("Request Processing Error: " + e);
        }
        System.out.println("Complete processing: " + socket1);
        System.out.println("Complete processing: " + socket2);
    }

    public void readFile(String tenFile){
        File file = new File(tenFile);
        if(file.isFile()){
            
        }
        
    }
}
