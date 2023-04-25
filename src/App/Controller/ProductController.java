package App.Controller;

import App.Model.ProductModel;
import App.Model.ProductTable;
import Entity.Employee;
import Entity.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ProductController {
    private ArrayList<ProductTable> productArrayList;
    private ProductModel productModel;
    private JTable table;
    public ProductController(){
        productModel = new ProductModel();
        getProductArrayList();
    }
    public ArrayList<ProductTable> getProductArrayList(){
        productArrayList = productModel.getData();
        return productArrayList;
    }
    public JTable setDataTable(){
        String[] columns = {"ID","Name", "Catagory","Create At","Delete At"};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (ProductTable product : productArrayList) {
            Object[] row = {product.getProductId(),product.getProductName(),product.getCategory(),product.getCreateAt(),product.getDeleteAt()};
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
    public Product InsertProduct(Product product){
        try {
            product = productModel.Insert(product);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Create Product", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e,
                    "Create Product", JOptionPane.INFORMATION_MESSAGE);
        }
        return product;
    }
    public void UpdateProduct(Product product){

    }
    public void DeleteProduct(Product product){

    }
}
