package DAL;

import App.Model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDAO extends DAO<Category>{
    @Override
    public ArrayList<Category> getAll() {
        Database dao = new Database();
        ArrayList<Category> categories = new ArrayList<>();
        Statement stmt = dao.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("select * from Category");
            while (rs!=null && rs.next()){
                categories.add(new Category(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category get(int id) {
        return null;
    }

    @Override
    public int create(Category category) {
        int id = 0;
        PreparedStatement pst = database.getPreStmt(
                "insert into category(id, category) values (?,?) returning id"
        );
        try{
            pst.setString(1, category.getCategoryName());
            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                id=rs.getInt(1);
                break;
            }
            category.setCategoryID(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void deleteById(int id) {

    }
}
