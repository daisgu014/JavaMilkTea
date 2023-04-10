package Entity;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private int totalPrice;
    private Date orderDate;
    private Employee cashier;
    private Customer customer;
    private ArrayList<OrderDetail> details;

    public Order() {
    }

    public Order(int orderId, int totalPrice, Date orderDate, Employee cashier, ArrayList<OrderDetail> details) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.cashier = cashier;
        this.details = details;
    }
    public Order(int orderId, int totalPrice, Date orderDate,Customer customer,Employee cashier) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.customer=customer;
        this.cashier = cashier;
    }

    public Order(int totalPrice, Date orderDate, Employee cashier, ArrayList<OrderDetail> details) {
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.cashier = cashier;
        this.details = details;
    }

    public int getOrderId() {
        return orderId;
    }
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Employee getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }

    public ArrayList<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<OrderDetail> details) {
        this.details = details;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
