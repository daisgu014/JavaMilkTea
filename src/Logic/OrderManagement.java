package Logic;


import DAL.OrderDAO;
import Entity.Order;
import Entity.OrderDetail;

import java.util.ArrayList;
public class OrderManagement {
    private ArrayList<Order> orders;
    private OrderDAO orderDAO = new OrderDAO();

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

}
