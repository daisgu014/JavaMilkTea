package App.View.CrudGUI;

import App.Controller.AccountController;
import Entity.Account;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccountGUI extends CrudGUI {
    private HashMap<Integer, Account> accounts;
    public AccountGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,btnExit,table,title);
    }
    public  AccountGUI(){
        setAccounts();
        Scene();
        SelectTable();
    }
    public void SceneAccount(){

    }
    public void getData(){
        AccountController accountController = new AccountController();
        accounts = accountController.getDataAccount();
    }
    public void setTitleAccount(){
        setTitle("Account");
    }
    public void setDataTable(){
        String[] columns = {"Employee ID","Username","Password"};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Map.Entry<Integer, Account> set: accounts.entrySet()){
            Object[] row = {set.getKey(),set.getValue().getUsername(),set.getValue().getPassword()};
            model.addRow(row);
        }
        table.setModel(model);
        setTable(table);
    }
    public void setButton(){
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete");
            }
        });
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
        setBtnExit(exit);
    }
    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(){
        getData();
        setTitleAccount();
        setDataTable();
        setButton();
    }
    public void SelectTable(){
        JTable table = getTable();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
//                    int col = table.getSelectedColumn();
                    Account account = new Account((String) table.getValueAt(row,1), (String) table.getValueAt(row,2));
                    AccountForm accountForm = new AccountForm();
                    accountForm.SceneAccount(account);
                    accountForm.getTfUsername().setEditable(false);
//                    setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        AccountGUI accountGUI = new AccountGUI();
//        accountGUI.setAccounts();
//        accountGUI.Scene();
//        accountGUI.SelectTable();
        jFrame.add(accountGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
