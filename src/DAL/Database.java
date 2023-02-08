package DAL;

import java.sql.*;

public class Database {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement preStmt;

    private final String connectString="jdbc:mysql://localhost:3307/MilkTea";
    private final String dbUser="admin";
    private final String dbPassword="123456";
    public Database(){
        try{
            conn = DriverManager.getConnection(connectString,dbUser,dbPassword);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Statement getStmt() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.err.println(this.getClass().toString());
            System.err.println(e.getMessage());
        }
        return stmt;
    }

    public PreparedStatement getPreStmt(String qry){
        try {
            preStmt = conn.prepareStatement(qry);
        } catch (SQLException e) {
            System.err.println(this.getClass().toString());
            System.err.println(e.getMessage());
        }
        return preStmt;
    }
}
