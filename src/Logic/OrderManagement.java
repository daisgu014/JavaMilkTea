package Logic;


import DAL.OrderDAO;
import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Main.Main;

import javax.swing.*;
import java.util.ArrayList;
public class OrderManagement {
    private ArrayList<Order> orders;
    private OrderDAO orderDAO = new OrderDAO();
    ProductManagement productManagement = new ProductManagement();
    CustomerManagement customerManagement = new CustomerManagement();

    public OrderManagement(){
        init();
    }
    public void init(){
        orders=orderDAO.getAll();
    }
    public ArrayList<Order> getOrders(){
        return orders;
    }
    public void setOrders(ArrayList<Order> orders){
        this.orders=orders;
    }
    public void insertOrderWithPhone(ArrayList<OrderDetail> orderDetails){
        Order newOrder = new Order();
        newOrder.setDetails(orderDetails);
        int sum=0;
        for(Order o : orders){
        }
    }
    public Order createWithPhone (ArrayList<OrderDetail> orderDetails, Customer customer, Integer price){
        Order order = new Order();
        order.setDetails(orderDetails);
        order.setTotalPrice(price);
        order.setCashier(Main.APP.currEmployee);
        order.setCustomer(customer);
        Order newOrder = orderDAO.create(order);
        order.setOrderId(newOrder.getOrderId());
        order.setOrderDate(newOrder.getOrderDate());
        for(OrderDetail o : orderDetails){
            if(orderDAO.insertOrderDetails(o,order)){
                productManagement.Sub_Storage_Product(o);
            }else{
            }
        }
        customerManagement.Update_Add_Point(order);
        return order;
    }
    public Order createWithNoPhone(ArrayList<OrderDetail> orderDetails){
        Order order = new Order();
        order.setDetails(orderDetails);
        int sum=0;
        for(OrderDetail p : orderDetails){
            sum+=(p.getProduct().getPrice(p.getSize())*p.getQuantity());
        }
        order.setCashier(Main.APP.currEmployee);
        order.setTotalPrice(sum);
        Order newOrder = orderDAO.CreateOrderWithNoPhone(order);
        order.setOrderId(newOrder.getOrderId());
        order.setOrderDate(newOrder.getOrderDate());
        for(OrderDetail o : orderDetails){
            if(orderDAO.insertOrderDetails(o,order)){
                productManagement.Sub_Storage_Product(o);
            }else {
            }
        }
        return order;
    }
    public Order findById(Integer id){
        if(orderDAO.get(id)!=null){
            return orderDAO.get(id);
        }else {
            return null;
        }
    }

    public ArrayList<OrderDetail> orderDetails(Integer id){
        return orderDAO.orderDetailsWithID(id);
    }
}
