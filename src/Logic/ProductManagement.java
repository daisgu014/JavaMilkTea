package Logic;

import App.Model.Product;
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
    public Product findById(int  CategoryId){
        return null;
    }

}

