package DAL;

import Entity.Import;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportDAO {
    public Database importDAO;
    public void updateStorageProduct(ArrayList<Import> list) {
        importDAO = new Database();
        PreparedStatement preStmt = importDAO.getPreStmt("""
                update ProductSize ps join Product p on ps.ProductID = p.ProductId
                set ps.Storage = (ps.Storage + ?)
                where p.ProductName = ? and ps.Sizes = ?;""");
        for (Import i : list) {
            try {
                preStmt.setInt(1, i.getQuantity());
                preStmt.setString(2, i.getProductName());
                preStmt.setString(3, i.getSize());
                preStmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
