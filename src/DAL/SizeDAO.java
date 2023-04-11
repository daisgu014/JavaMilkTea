package DAL;

import Entity.Size;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SizeDAO extends DAO<Size>
{
    Database dao = new Database();
    String getAllSizeQuery = "select * from  Sizes;";
    @Override
    public ArrayList<Size> getAll() {
        ArrayList<Size> sizes = new ArrayList<>();
        Statement statement = dao.getStmt();
        try {
            ResultSet rs = statement.executeQuery(getAllSizeQuery);
            while (rs.next()){
                sizes.add(new Size(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("SizeDAO");
            System.out.println(e.getMessage());
        }
        return sizes;
    }

    @Override
    public Size get(int id) {
        return null;
    }

    @Override
    public int create(Size size) {
        return 0;
    }

    @Override
    public void update(Size size) {

    }

    @Override
    public void delete(Size size) {

    }

    @Override
    public void deleteById(int id) {

    }
    public Size findByName(String sizeName){
        Size size = new Size();
        PreparedStatement prSt= dao.getPreStmt("select * from  Sizes where sign like ?;");
        try {
            prSt.setString(1,sizeName);
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                size.setSign(rs.getString(1));
                size.setDescription(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return size;
    }
}
