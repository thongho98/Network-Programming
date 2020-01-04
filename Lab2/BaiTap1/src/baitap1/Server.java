/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author HOQUOCTHONG
 */
public class Server {
    public static final int NUM_OF_THREAD = 4;
    public final static int SERVER_PORT = 7;
    
    public Server() throws IOException{
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);
        ServerSocket serverSocket = null;
        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");
            while (true) {
                try {
                    Socket socket1 = serverSocket.accept();
                    System.out.println("Client accepted: " + socket1);
                    Socket socket2 = serverSocket.accept();
                    System.out.println("Client accepted: " + socket2);
                    
                    HandleInfo handler = new HandleInfo(socket1, socket2);
                    executor.execute(handler);
                } catch (IOException e) {
                    System.err.println(" Connection Error: " + e);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
    
    
    
    public static void main(String[] args) throws IOException{
        Server s = new Server();
    }
}
