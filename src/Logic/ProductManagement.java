package Logic;

import Entity.Product;
import DAL.ProductDAO;

import java.util.ArrayList;

public class ProductManagement {
    private ArrayList<Product> products;
    private ProductDAO productDAO = new ProductDAO();

    public ProductManagement() {
        init();
    }
    private void init(){
        products = productDAO.getAll();
    }
    public ArrayList<Product> getProducts(){
        return products;
    }
    public void setProducts(ArrayList<Product> CategoryList){
        this.products = CategoryList;
    }
    public Product findById(int  productId){
        return productDAO.get(productId);
    }

    public ArrayList<String> getProductNameList() {
        ArrayList<String> names = new ArrayList<>();
        for(Product p : products) {
            names.add(p.getProductName());
        }
        return names;
    }
}

