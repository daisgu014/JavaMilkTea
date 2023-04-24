package DAL;

import Entity.OrderDetail;
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
    public ArrayList<ProductSize> productSizesWithProductId(int id){
        ArrayList<ProductSize> productSizes = new ArrayList<>();
        Statement statement = database.getStmt();
        try {
            ResultSet resultSet = statement.executeQuery("select * from ProductSize where ProductID ="+id   );
            while (resultSet.next()){
                productSizes.add(new ProductSize(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productSizes;
    }

    @Override
    public Product  get(int id) {
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
            product.setProductSizes(productSizesWithProductId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return product;
    }

    @Override
    public Product create(Product product) {
        return null;
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
    public void Sub_Storage_Product(OrderDetail orderDetail){
        Integer afterStorage = orderDetail.getProduct().getQty(orderDetail.getSize())-orderDetail.getQuantity();
        PreparedStatement prSt = dao.getPreStmt("update ProductSize set Storage = ? where productID = ? and Sizes like ?;");
        try {
            prSt.setInt(1,afterStorage);
            prSt.setInt(2,orderDetail.getProduct().getProductId());
            prSt.setString(3,orderDetail.getSize());
            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sub_Storage_Product");
            System.out.println(e.getMessage());
        }
    }

}
