package DAL;

import App.Model.Category;

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
        return -1;
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
