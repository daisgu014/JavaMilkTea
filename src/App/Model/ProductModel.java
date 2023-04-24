package App.Model;

import Entity.Product;
import Logic.ProductManagement;

import java.util.ArrayList;

public class ProductModel {
    private ProductManagement productManagement;
    public ProductModel(){
        productManagement = new ProductManagement();
    }
    public ArrayList<Product> getData(){
        return productManagement.getProducts();
    }
    public Product Insert(Product product){
        return productManagement.Insert(product);
    }
    public void Update(Product product){
        productManagement.Update(product);
    }
    public void Delete(Product product){
        productManagement.Delete(product);
    }
}
