package DAL;

import Entity.Customer;

import java.lang.reflect.Member;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAO extends DAO<Customer> {
    Database dao = new Database();

    /**
     *
     *  private String phone;
     *     private String customerName;
     *     private int points;
     * @return
     */
    @Override
    public ArrayList<Customer> getAll() {
       ArrayList<Customer> customers = new ArrayList<>();
        Statement statement = dao.getStmt();
        try {
            ResultSet rs = statement.executeQuery("select * from Customer where deleteAt is  null");
            while (rs.next()){
                customers.add(new Customer(rs.getString(1),rs.getString(2),rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return customers;
    }

    @Override
    public Customer get(int pr) {
        return null;
    }

    @Override
    public Customer create(Customer customer) {
        Customer newCustomer = null;

        return newCustomer;
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement prSt = dao.getPreStmt("update Customer set CustomerName= ?, Points= ? where Phone= ?;");
        try {
            prSt.setString(1,customer.getCustomerName());
            prSt.setInt(2,customer.getPoints());
            prSt.setString(3,customer.getPhone());
            prSt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Update Customer");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Customer customer) {
        PreparedStatement prSt = dao.getPreStmt("update Customer set deleteAt=CURDATE() where Phone=?;");
        try {
            prSt.setString(1,customer.getPhone());
            prSt.executeUpdate();

        }catch (SQLException e){
            System.out.println("Delete Customer");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {

    }
    public Customer findByPhone(String phone){
        Customer customer = new Customer();
        PreparedStatement prSt= dao.getPreStmt("select * from Customer where Phone like ?; ");
        try {
            prSt.setString(1,phone);
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                customer.setPhone(rs.getString(1));
                customer.setCustomerName(rs.getString(2));
                customer.setPoints(rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return customer;
    }
    public void insertCustomer(Customer  customer){
        PreparedStatement prSt = dao.getPreStmt("insert into Customer(Phone, CustomerName, Points) values(?,?,?);");
        try {
            prSt.setString(1,customer.getPhone());
            prSt.setString(2,customer.getCustomerName());
            prSt.setInt(3,customer.getPoints());
            prSt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Customer findByName(String name){
        Customer customer = new Customer();
        PreparedStatement prSt = dao.getPreStmt("select * from Customer where customerName like ?;");
        try{
            prSt.setString(1, name);
            ResultSet rs= prSt.executeQuery();
            while (rs.next()){
                customer.setPhone(rs.getString(1));
                customer.setCustomerName(rs.getString(2));
                customer.setPoints(rs.getInt(3));
            }
        }catch (SQLException e){
            System.out.println("Find By Name");
            System.out.println(e.getMessage());
        }
        return customer;
    }
    public void Update_Sub_Point(Customer customer, Integer point){
        PreparedStatement prSt = dao.getPreStmt("update Customer set Points = Points - ? where Phone like ?");
        try{
            prSt.setInt(1,point);
            prSt.setString(2,customer.getPhone());
            prSt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Update_Sub_Point");
            System.out.println(e.getMessage());
        }

    }
}
