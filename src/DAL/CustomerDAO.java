package DAL;

import Entity.Customer;

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
            ResultSet rs = statement.executeQuery("select * from Customer");
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
    public int create(Customer customer) {
        return 0;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

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

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.findByPhone("0939123456");

    }
}
