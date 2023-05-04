package Logic;

import App.View.Shop.loadData;
import Entity.Customer;
import DAL.CustomerDAO;
import Entity.Order;

import javax.swing.*;
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
           if(customerDAO.insertCustomer(customer)){
               JOptionPane.showMessageDialog(null, "Thêm khách hàng "+ customer.getCustomerName()+" thành công");
           }else {
               JOptionPane.showMessageDialog(null, "Thêm khách hàng không thành công");
           }
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
    public void Update_Add_Point(Order order){
        Integer point = order.getTotalPrice()/100000;
        if(point>0){
            if(customerDAO.Update_Add_Point(order.getCustomer().getPhone(),point)){
                for(Customer c :loadData.customers){
                    if(c.getPhone().equalsIgnoreCase(order.getCustomer().getPhone())){
                        c.setPoints(c.getPoints()+point);
                    }
                }
                System.out.println("Cập nhật điểm thành công");
            }else {
                System.out.println("Cập nhật điểm thất bại");
            }
        }
    }
}
