package DAL;

import Entity.Order;
import Entity.OrderDetail;
import Logic.CustomerManagement;
import Logic.EmployeeManagement;
import Logic.ProductManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDAO extends DAO<Order>{
    Database database = new Database();
    CustomerManagement customerManagement =new CustomerManagement();
    EmployeeManagement employeeManagement = new EmployeeManagement();
    ProductManagement productManagement = new ProductManagement();
    @Override
    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        Statement statement = database.getStmt();
        try {
            ResultSet rs = statement.executeQuery("select * from Orders");
            while (rs.next()){
                orders.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        customerManagement.findByPhone(rs.getString(4)),
                        employeeManagement.getEmployeeById(rs.getInt(5))));
}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement prSt = database.getPreStmt("select * from OrderDetail where OrderID=?");
        for(Order order : orders){
            ArrayList<OrderDetail> orderDetails = new ArrayList<>();
            try {
                prSt.setInt(1,order.getOrderId());
                ResultSet resultSet = prSt.executeQuery();
                while (resultSet.next()){
                    orderDetails.add(new OrderDetail(
                            productManagement.findById(resultSet.getInt(2)),
                            resultSet.getString(3),
                            resultSet.getInt(4)));

                }
                order.setDetails(orderDetails);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return orders;
    }

    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public int create(Order order) {
        PreparedStatement prSt = database.getPreStmt("insert into Orders(OrderId,TotalPrice,CustomerPhone,Cashier) values(?,?,?,?) returning orderId");
        try {
            prSt.setInt(1,order.getOrderId());
            prSt.setInt(2,order.getTotalPrice());
            prSt.setString(3,order.getCustomer().getPhone());
            prSt.setInt(4,order.getCashier().getEmployeeId());
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("OrderDAO");
            System.out.println(e.getMessage());
        }

        return 0;
    }
    public int CreateOrderWithNoPhone(Order order){
        PreparedStatement prSt = database.getPreStmt("insert into Orders(OrderId,TotalPrice,Cashier) values(?,?,?) returning orderId");
        try {
            prSt.setInt(1,order.getOrderId());
            prSt.setInt(2,order.getTotalPrice());
            prSt.setInt(3,order.getCashier().getEmployeeId());
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("OrderDAO");
            System.out.println(e.getMessage());
        }

        return 0;
    }
    public void insertOrderDetails(OrderDetail orderDetail, Order order){
        PreparedStatement prSt=database.getPreStmt("insert into OrderDetail(OrderID, ProductID, Sizes, Quantity) value(?,?,?,?)");
        try {
            prSt.setInt(1,order.getOrderId());
            prSt.setInt(2,orderDetail.getProduct().getProductId());
            prSt.setString(3, orderDetail.getSize());
            prSt.setInt(4,orderDetail.getQuantity());
            prSt.executeQuery();
        } catch (SQLException e) {
            System.out.println("OrderDAO");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void deleteById(int id) {

    }
}
