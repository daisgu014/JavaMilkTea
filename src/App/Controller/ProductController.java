package App.Controller;

import App.Model.ProductModel;
import Entity.Employee;
import Entity.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ProductController {
    private ArrayList<Product> productArrayList;
    private ProductModel productModel;
    private JTable table;
    public ProductController(){
        productModel = new ProductModel();
        getProductArrayList();
    }
    public ArrayList<Product> getProductArrayList(){
        productArrayList = productModel.getData();
        return  productArrayList;
    }
    public JTable setDataTable(){
        String[] columns = {"ID","Name", "Catagory","Create At","Delete At"};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Product product : productArrayList) {
            Object[] row = {product.getProductId(),product.getProductName(),product.getCategory(),product.getCreateAt(),product.getDeleteAt()};
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
    public Product InsertProduct(Product product){

        return product;
    }
    public void UpdateProduct(Product product){

    }
    public void DeleteProduct(Product product){

    }
}
