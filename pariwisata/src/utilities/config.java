package utilities;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;

public class config {
    public static Connection getConnection(){
        
        Connection connection = null;
        String driver  = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/pariwisata";
        String user = "root";
        String password = "";
        
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } 
            catch (ClassNotFoundException | SQLException error) {
                System.err.println("Error at Koneksi-getConnection, details : "+ error.toString());
                JOptionPane.showMessageDialog(null, "Error at koneksi-getConnection, details :" + error.toString());
                System.exit(0);
            }
        }
            return connection;
    }
}
