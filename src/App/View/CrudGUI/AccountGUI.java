package App.View.CrudGUI;

import Entity.Account;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class AccountGUI extends CrudGUI {
    private ArrayList<Account> accountArrayList;
    public AccountGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,btnExit,table,title);
    }
    public  AccountGUI(){

    }

    public ArrayList<Account> getAccountArrayList() {
        return accountArrayList;
    }
    public void setAccountArrayList(ArrayList<Account> accountArrayList) {
        this.accountArrayList = accountArrayList;
    }

    public void SceneAccount(){

    }
    public void getData(){

    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,1000);

        String[] columns = {"Name", "Designation"}; //, "Salary"
        Object[][] rows = {{"Adithya", "Content Developer", 25000},
                {"Jai", "SME", 30000},
                {"Chaitanya", "Java Engineer", 45000},
                {"Ramesh", "Scala Developer", 40000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000}
        };
        JTable jTable = new JTable(rows,columns);
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        JButton exit = new JButton("Exit");
        CrudGUI crudGUI = new CrudGUI(add,edit,delete,exit,jTable,"Account");
        crudGUI.SceneCRUD();
        jFrame.add(crudGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
