package DAL;

import Entity.Category;
import Entity.WorkPosition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDAO extends DAO<Category>{
    Database dao = new Database();
    @Override
    public ArrayList<Category> getAll() {

        ArrayList<Category> categories = new ArrayList<>();
        Statement stmt = dao.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("select * from Category WHERE DeleteAt is NULL");
            while (rs!=null && rs.next()){
                categories.add(new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getDate(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category get(int id) {
        Category category = new Category();
        PreparedStatement statement = dao.getPreStmt("select * from Category where CategoryId = ? and deleteAt is null;");
        try {
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while ((rs.next())){
                category.setCategoryID(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
                category.setCreateAt(rs.getDate(3));
                category.setDeleteAt(rs.getDate(4));
            }
        } catch (SQLException e) {
            System.out.println("CategoryDAO (get)");
            System.out.println(e.getMessage());
        }
     return  category;
    }

    @Override
    public Category create(Category category) {
        try {
            PreparedStatement prSt = database.getPreStmt("INSERT INTO category(CategoryName)  VALUES(?)");
            prSt.setString(1, category.getCategoryName());
            prSt.execute();
            PreparedStatement prStSelect = database.getPreStmt("SELECT * FROM category Order by CategoryId");
            ResultSet rs = prStSelect.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getDate(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }


    @Override
    public void update(Category category) {
        try {
            PreparedStatement prSt = database.getPreStmt("UPDATE category SET CategoryName = ?  WHERE CategoryId = ?");
            prSt.setString(1, category.getCategoryName());
            prSt.setInt(2,category.getCategoryID());
            prSt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Category category) {
        try {
            PreparedStatement prSt = database.getPreStmt("UPDATE category SET DeleteAt = CURDATE()  WHERE CategoryId = ?");
            prSt.setInt(1,category.getCategoryID());
            prSt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {

    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.get(2).getCategoryName();
    }
}

