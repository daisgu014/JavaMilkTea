package App.View.Shop;

import Entity.Category;
import Entity.Product;
import Logic.Management;

import java.util.ArrayList;

public class loadData {
    public static Management management = new Management();
    public  static ArrayList<Product> products =management.getProductManagement().getProducts();
    public  static ArrayList<Category> categories = management.getCategoryManagement().getCategoryList();
}
