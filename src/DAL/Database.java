package DAL;

import java.sql.*;

public class Database {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement preStmt;

    public Database(){
        conn = DatabaseConnection.getInstance().getConnection();
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
