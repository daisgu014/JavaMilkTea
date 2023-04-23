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
    public Customer findByName (String name){
       return customerDAO.findByName(name);
    }
    public void Update_Sub_Point(Customer customer, Integer point){
        customerDAO.Update_Sub_Point(customer,point);
    }
    public  ArrayList<String> PointOfCustomer (Customer customer){
        ArrayList<String> arrayList = new ArrayList<>();
        int countPoint=customer.getPoints()/10;
        if(countPoint>0){
            for(int i =1;i<=countPoint;i++){
                arrayList.add(String.valueOf(i*10));
            }
        }
        return arrayList;
    }
}
