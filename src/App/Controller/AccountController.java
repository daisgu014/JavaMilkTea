package App.Controller;

import App.Model.AccountModel;
import DAL.AccountDAO;
import Entity.Account;
import Entity.Employee;
import Logic.AccountManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class AccountController {
    private HashMap<Integer, Account> accounts;
    private final AccountModel accountModel = new AccountModel();
    private JTable table;
    public HashMap<Integer, Account> getDataAccount(){
        accounts = accountModel.getDataAccount();
        return  accounts;
    }
    public JTable getDataTable(){
        String[] columns = {"Employee ID","Username","Password"};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Map.Entry<Integer, Account> set: accounts.entrySet()){
            Object[] row = {set.getKey(),set.getValue().getUsername(),set.getValue().getPassword()};
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
    public void InsertAccount(Account account,String empID){
        try {
            accountModel.InsertAccount(account,empID);
            JOptionPane.showMessageDialog(null, "Thêm thành công !",
                    "Add Account",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!",
                    "Add Account",JOptionPane.INFORMATION_MESSAGE);
//            System.out.println(e);
        }
    }
    public void UpdateAccount(Account account) {
        try {
            accountModel.UpdateAccount(account);
            JOptionPane.showMessageDialog(null, "Thành công !",
                    "Upadate Account", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void DeleteAccount(Account account){
        try {
            accountModel.DeleteAccount(account);
            JOptionPane.showMessageDialog(null, "Thành công !",
                    "Delete Account", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "Delete Account", JOptionPane.INFORMATION_MESSAGE);
//            System.out.println(e);
        }
    }
}
