package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private Connection connection = null;
    private final String connectString="jdbc:mysql://localhost:3307/MilkTea";
    private final String dbUser="admin";
    private final String dbPassword="123456";

    private DatabaseConnection() {
        try {
            // Tạo kết nối đến database ở đây
            connection = DriverManager.getConnection(connectString,dbUser,dbPassword);
        } catch (SQLException ex) {
            System.out.println("Không thể kết nối đến database.");
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
