package Logic;

import Entity.Customer;
import DAL.CustomerDAO;

import java.util.ArrayList;

public class CustomerManagement {
    private ArrayList<Customer> customers = new ArrayList<>();
    private CustomerDAO customerDAO = new CustomerDAO();

    public CustomerManagement(){
        init();
    }
    public void init(){
        customers=customerDAO.getAll();
    }
    public void create(Customer customer){
           customerDAO.insertCustomer(customer);
    }
    public void update(Customer customer){
        customerDAO.update(customer);
    }
    public void delete(Customer customer){
        customerDAO.delete(customer);
    }
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public void setCustomers(ArrayList<Customer> customers){
        this.customers=customers;
    }
    public Customer findByPhone(String phone){
        return customerDAO.findByPhone(phone);
    }

}
