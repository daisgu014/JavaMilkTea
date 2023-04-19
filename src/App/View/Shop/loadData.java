package App.View.Shop;

import Entity.*;
import Logic.Management;

import java.util.ArrayList;

public class loadData {
    public static Management management = new Management();
    public  static ArrayList<Product> products =management.getProductManagement().getProducts();
    public  static ArrayList<Category> categories = management.getCategoryManagement().getCategoryList();
    public static ArrayList<Order> orders = management.getOrderManagement().getOrders();
    public static ArrayList<Customer> customers = management.getCustomerManagement().getCustomers();

    public static ArrayList<OrderDetail> orderDetails = new ArrayList<>();
}
