package App.Model;

import Entity.Customer;
import Entity.Employee;
import Entity.Order;

import java.sql.Date;

public class OrderModel {
    private Integer OrderId;
    private Customer customer;
    private Date date;
    private Integer totalPrice;
    private Employee employee;
    private Order order;

    public OrderModel(Integer orderId, Customer customer, Date date, Integer totalPrice, Employee employee) {
        OrderId = orderId;
        this.customer = customer;
        this.date = date;
        this.totalPrice = totalPrice;
        this.employee = employee;
    }
    public OrderModel(Integer orderId, Customer customer, Date date, Integer totalPrice, Employee employee, Order order) {
        OrderId = orderId;
        this.customer = customer;
        this.date = date;
        this.totalPrice = totalPrice;
        this.employee = employee;
        this.order = order;
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
