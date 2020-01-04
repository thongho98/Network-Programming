package baitapcsdl;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

    public static Connection cnn = null;

    public static Connection getConnection() {
        try {
            String dbURL = "jdbc:sqlserver://;databaseName=KIEMTRALTM;user=sa;password=123";
            cnn = DriverManager.getConnection(dbURL);
            if (cnn != null) {
                System.out.println("Connected");
            }
        } catch (Exception e) {
            System.err.println("Ket noi that bai.");
        }
        return cnn;
    }
}
