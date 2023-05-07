package App.Model;

import Entity.Product;
import Entity.ProductSize;
import Logic.ProductManagement;

import java.util.ArrayList;

public class ProductModel {
    private ProductManagement productManagement;
    public ProductModel(){
        productManagement = new ProductManagement();
    }
    public ArrayList<ProductTable> getData(){
        return productManagement.getData();
    }
    public ArrayList<Product> getAllData() {return  productManagement.getProducts();}
    public Product Insert(Product product){
        return productManagement.Insert(product);
    }
    public void Update(Product product){
        productManagement.Update(product);
    }
    public void Delete(Product product){
        productManagement.Delete(product);
    }
    public void InsetSize(int productId, ProductSize productSize){
        productManagement.InsetSize(productId,productSize);
    }
    public void UpdateSize(int productId, ProductSize productSize){
        productManagement.UpdateSize(productId,productSize);
    }
}
