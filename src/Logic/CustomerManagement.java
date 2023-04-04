package Logic;

import App.Model.Customer;
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
