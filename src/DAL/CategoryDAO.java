package DAL;

import Entity.Category;

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
            ResultSet rs = stmt.executeQuery("select * from Category");
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

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.get(2).getCategoryName();
    }
}

