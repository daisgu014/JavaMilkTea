package App.Controller;

import App.Model.ProductModel;
import App.Model.ProductTable;
import Entity.Employee;
import Entity.Product;
import Entity.ProductSize;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ProductController {
    private ArrayList<ProductTable> productTableArrayList;
    private ArrayList<Product> productArrayList;
    private ProductModel productModel;
    private JTable table;
    public ProductController(){
        productModel = new ProductModel();
        getProductArrayList();
    }
    public ArrayList<ProductTable> getProductArrayList(){
        productTableArrayList = productModel.getData();
        return productTableArrayList;
    }
    public ArrayList<Product> getProductAll(){
        productArrayList = productModel.getAllData();
        return productArrayList;
    }
    public JTable setDataTable(){
        String[] columns = {"ID","Name", "Catagory","Create At","Delete At"};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (ProductTable product : productTableArrayList) {
            Object[] row = {
                    product.getProductId(),
                    product.getProductName(),
                    product.getCategory(),
                    product.getCreateAt(),
                    product.getDeleteAt()
            };
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
    public JTable setDataTableSize(){
        String[] columns = {"ID","Name", "Size","Price","Storage"};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Product product : productArrayList) {
            if (product.getProductSizes().size()!=0){
                for (ProductSize productSize : product.getProductSizes()){
                    Object[] row = {
                            product.getProductId(),
                            product.getProductName(),
                            productSize.getSize(),
                            productSize.getProductPrice(),
                            productSize.getStorage()
                    };
                    model.addRow(row);
                }
            }else {
                Object[] row = {product.getProductId(),product.getProductName(),null,null,null};
                model.addRow(row);
            }
        }
        table.setModel(model);
        return table;
    }
    public Product InsertProduct(Product product){
        try {
            product = productModel.Insert(product);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Create Product", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e,
                    "Create Product", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("src/Assets/Icons/cancel.png")
            );
        }
        return product;
    }
    public void UpdateProduct(Product product){
        try {
            productModel.Update(product);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Update Product", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
                    );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e,
                    "Update Product", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/cancel.png")
                    );
        }
    }
    public void DeleteProduct(Product product){
        try {
            productModel.Delete(product);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Delete Product", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
                    );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e,
                    "Delete Product", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/cancel.png")
                    );
        }
    }
    public void InsertProductSize(int productId,ProductSize productSize){
        try {
            productModel.InsetSize(productId,productSize);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Create Product Size", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
                    );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e,
                    "Create Product Size", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/cancel.png")
                    );
        }
    }
    public void UpdateProductSize(int productId,ProductSize productSize){
        try {
            productModel.UpdateSize(productId,productSize);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Update Product Size", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
                    );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e,
                    "Update Product Size", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/cancel.png")
                    );
        }
    }
}
