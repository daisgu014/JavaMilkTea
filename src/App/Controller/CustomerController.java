package App.Controller;

import App.View.CustomerGUI;
import Entity.Customer;
import Logic.CustomerManagement;

import javax.swing.*;

import static App.View.Shop.loadData.customers;

public class CustomerController {
    CustomerManagement customerManagement = new CustomerManagement();
    private CustomerGUI customerGUI;
    public CustomerController(){

    }

    public CustomerController(CustomerGUI customerGUI){
        this.customerGUI = customerGUI;
    }

    public CustomerGUI getCustomerGUI() {
        return customerGUI;
    }

    public void setCustomerGUI(CustomerGUI customerGUI) {
        this.customerGUI = customerGUI;
    }
}
