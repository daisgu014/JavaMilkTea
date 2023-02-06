package DAL;

import App.Model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDAO {
    public CategoryDAO(){

    }
    public static ArrayList<Category> retrieve(){
        DAO dao = new DAO();
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
}
