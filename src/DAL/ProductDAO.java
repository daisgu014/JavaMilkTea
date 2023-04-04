package DAL;

import App.Model.Category;
import App.Model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO  extends DAO<Product> {
    Database dao = new Database();
    String getAllProductQuery = "select * from Product where deleteAt is null";
    @Override
    public ArrayList<Product> getAll() {

        ArrayList<Product> products = new ArrayList<>();
        Statement stmt = dao.getStmt();
        try {
            ResultSet rs = stmt.executeQuery(getAllProductQuery);
            while (rs!=null && rs.next()){
               products.add(new Product(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getString(4),rs.getDate(5),rs.getDate(6))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Product p : products){
            System.out.println(p.getProductName());
        }
        return products;
    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public int create(Product product) {
        return 0;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void deleteById(int id) {

    }

}
