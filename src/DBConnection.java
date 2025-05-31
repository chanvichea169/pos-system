/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Admin
 */
public class DBConnection {
  public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/testing_java?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Phnom_Penh";
        String user = "root";  
        String password = "";  

        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
        
        return conn;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
