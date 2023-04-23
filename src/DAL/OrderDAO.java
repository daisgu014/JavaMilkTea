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
                orders.add(new Order(
                        rs.getInt(1),
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
    public Order create(Order order) {
        Order newOrder = new Order();
        PreparedStatement prSt = database.getPreStmt("insert into Orders(TotalPrice,CustomerPhone,Cashier) values(?,?,?)");
        try {
            prSt.setInt(1,order.getTotalPrice());
            prSt.setString(2,order.getCustomer().getPhone());
            prSt.setInt(3,order.getCashier().getEmployeeId());
            prSt.executeUpdate();
            Statement statement = database.getStmt();
            ResultSet resultSet = statement.executeQuery("select o.* from Orders o order by o.OrderId desc limit 1;");
            while (resultSet.next()){
                newOrder.setOrderId(resultSet.getInt(1));
                newOrder.setTotalPrice(resultSet.getInt(2));
                newOrder.setOrderDate(resultSet.getDate(3));
                newOrder.setCashier(employeeManagement.getEmployeeById(resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println("OrderDAO");
            System.out.println(e.getMessage());
        }

        return newOrder;
    }
    public Order CreateOrderWithNoPhone(Order order){
        Order newOrder = new Order();
        PreparedStatement prSt = database.getPreStmt("insert into Orders(TotalPrice,Cashier) values(?,?)");
        try {
            prSt.setInt(1,order.getTotalPrice());
            prSt.setInt(2,order.getCashier().getEmployeeId());
            prSt.executeUpdate();
            Statement statement = database.getStmt();
            ResultSet rs = statement.executeQuery("select o.* from Orders o order by o.OrderId desc limit 1");
            while (rs.next()){
                newOrder.setOrderId(rs.getInt(1));
                newOrder.setTotalPrice(rs.getInt(2));
                newOrder.setOrderDate(rs.getDate(3));
                newOrder.setCustomer(customerManagement.findByPhone(rs.getString(4)));
                newOrder.setCashier(employeeManagement.getEmployeeById(rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println("OrderDAO");
            System.out.println(e.getMessage());
        }

        return newOrder;
    }
    public boolean insertOrderDetails(OrderDetail orderDetail, Order order){
        PreparedStatement prSt=database.getPreStmt("insert into OrderDetail(OrderID, ProductID, Sizes, Quantity) value(?,?,?,?)");
        try {
            prSt.setInt(1,order.getOrderId());
            prSt.setInt(2,orderDetail.getProduct().getProductId());
            prSt.setString(3, orderDetail.getSize());
            prSt.setInt(4,orderDetail.getQuantity());
            prSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("OrderDAO");
            System.out.println(e.getMessage());
        }
    return false;
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
    public ArrayList<OrderDetail> orderDetailsWithID(Integer orderID){
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        try {
            PreparedStatement prSt = database.getPreStmt("select distinct od.OrderId, od.productId, od.Quantity, od.Sizes from OrderDetail od where od.OrderId = ?;");
            prSt.setInt(1,orderID);
            ResultSet rs = prSt.executeQuery();
            System.out.println(rs.getRow());
            while (rs.next()){
                orderDetails.add(new OrderDetail(
                        productManagement.findById(rs.getInt(2)),rs.getString(4),rs.getInt(3)
                ));
            }
        }catch(SQLException e){
            System.out.println("OrderDetailsWithID");
            System.out.println(e.getMessage());
        }
        return orderDetails;
    }
}
