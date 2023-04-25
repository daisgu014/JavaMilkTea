package Logic;

import App.Model.ProductTable;
import Entity.OrderDetail;
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
    
    public Product findByName (String name){
        Product product = null;
       for(Product i : products){
           if(i.getProductName().equalsIgnoreCase(name)) {
               product = i;
           }
       }
       return product;
    }

    public ArrayList<String> getProductNameList() {
        ArrayList<String> names = new ArrayList<>();
        for(Product p : products) {
            names.add(p.getProductName());
        }
        return names;
    }
    public void Sub_Storage_Product(OrderDetail orderDetail){
        productDAO.Sub_Storage_Product(orderDetail);
    }
    public Product Insert(Product product){
        return productDAO.create(product);
    }
    public void Update(Product product){
        productDAO.update(product);
    }
    public void Delete(Product product){
        productDAO.delete(product);
    }
    public ArrayList<ProductTable> getData(){
       return productDAO.getProductWithCateName();
    }
}

