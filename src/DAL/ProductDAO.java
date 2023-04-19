package DAL;

import Entity.Product;
import Entity.ProductSize;

import java.sql.PreparedStatement;
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
            while (rs.next()){
               products.add(new Product(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getString(4),rs.getDate(5),rs.getDate(6))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Product p : products){
            ArrayList<ProductSize> productSizes = new ArrayList<>();
            PreparedStatement preparedStatement = dao.getPreStmt("select * from ProductSize where ProductID=?;");
            try {
                preparedStatement.setInt(1,p.getProductId());
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println();
                while (resultSet.next()){
                    productSizes.add(new ProductSize(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4)));
                }
            p.setProductSizes(productSizes);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return products;
    }

    @Override
    public Product get(int id) {
        Product product = new Product();
        PreparedStatement statement = dao.getPreStmt("select * from Product where ProductId=?");
        try {
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                product.setProductId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setCategory(rs.getInt(3));
                product.setImagePath(rs.getString(4));
                product.setCreateAt(rs.getDate(5));
                product.setDeleteAt(rs.getDate(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return product;
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
