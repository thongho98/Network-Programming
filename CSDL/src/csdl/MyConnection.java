/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csdl;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HOQUOCTHONG
 */
public class MyConnection {

    static Connection cnn = null;

    public static Connection getConnection() {
        try {
            String dbURL = "jdbc:sqlserver://localhost;databaseName=wineshop;user=sa;password=123";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (Exception e) {
            System.err.println("Ket noi that bai.");
        }
        return cnn;
    }
}
