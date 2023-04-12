package App.View;

import Entity.Account;
import App.Model.CRUDForm;
import DAL.AccountDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountForm extends CRUDForm{
//    private JButton btnAdd;
//    private JButton btnUpdate;
//    private JButton btnDelete;
//    private JButton btnExit;
//    private JTable table;
//    private String title;
    private ArrayList<Account> accountArrayList;
    private int indexSelected;

    public AccountForm(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title) {
        super(btnAdd,btnUpdate,btnDelete,btnExit,table,title);
    }
    public AccountForm(){

    }
    public void SelectTable(){
        JTable table = getTable();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
//                    int col = table.getSelectedColumn();
                    indexSelected = row;
                }
            }
        });
    }
    public void AddButton(){
        accountArrayList = getData();
        getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(accountArrayList.get(indexSelected).getUsername());
            }
        });
    }
    public void ExitButtion(){
        getBtnExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public ArrayList<Account> getData(){
        ArrayList<Account> accounts = new ArrayList<>();
        AccountDAO accountDAO = new AccountDAO();
        accounts = accountDAO.getAll();
        return accounts;
    }
    public void setDataTable(){
        String[] columns = {"User", "Password"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        accountArrayList = getData();
        for (int i = 0 ; i < accountArrayList.size(); i++){
            model.addRow(new Object[]{accountArrayList.get(i).getUsername(),accountArrayList.get(i).getPassword()});
        }
        getTable().setModel(model);
    }
    public void SceneAccount(){
        SceneCRUD();
        setDataTable();
    }
    public static void main(String[] args) {
        Object columns[] = {"User", "Password"};
        JTable table = new JTable();
        JButton btnAdd = new JButton("ADD");
        JButton btnUpdate = new JButton("EDIT");;
        JButton btnDelete =new JButton("DELETE");;
        JButton btnExit = new JButton("EXIT");;
//        CRUDForm crud = new CRUDForm(btnAdd,btnUpdate,btnDelete,btnExit,table,"Account");
//        crud.SceneCRUD();
        AccountForm accountForm1 = new AccountForm();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (int i = 0 ; i < accountForm1.getData().toArray().length; i++){
//            System.out.println(accountForm1.getData().get(i).getUsername());
            model.addRow(new Object[]{accountForm1.getData().get(i).getUsername(),accountForm1.getData().get(i).getPassword()});
        }
        table.setModel(model);
        AccountForm accountForm = new AccountForm(btnAdd,btnUpdate,btnDelete,btnExit,table,"Account");
        accountForm.SceneCRUD();
        accountForm.ExitButtion();
        accountForm.SelectTable();
        accountForm.AddButton();

    }
}
