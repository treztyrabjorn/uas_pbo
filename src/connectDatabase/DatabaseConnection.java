/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connectDatabase;

/**
 *
 * @author Zwesty Quatra
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/university";
            String username = "root";  // Ganti dengan username MySQL Anda
            String password = "";  // Ganti dengan password MySQL Anda
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException("Tidak dapat menghubungkan ke database", e);
        }
    }
}

