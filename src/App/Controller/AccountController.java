package App.Controller;

import App.Model.AccountModel;
import DAL.AccountDAO;
import Entity.Account;
import Logic.AccountManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class AccountController {
    private HashMap<Integer, Account> accounts;
    private final AccountModel accountModel = new AccountModel();
    public HashMap<Integer, Account> getDataAccount(){
        accounts = accountModel.getDataAccount();
        return  accounts;
    }
    public boolean InsertAccount(Account account,String empID){
        try {
            accountModel.InsertAccount(account,empID);
            JOptionPane.showMessageDialog(null, "Thêm thành công !",
                    "Add Account",JOptionPane.INFORMATION_MESSAGE);
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!",
                    "Add Account",JOptionPane.INFORMATION_MESSAGE);
            return false;
//            System.out.println(e);
        }
    }
    public void UpdateAccount(Account account) {
        try {
            accountModel.UpdateAccount(account);
            JOptionPane.showMessageDialog(null, "Thành công !",
                    "Upadate Account", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "Upadate Account", JOptionPane.INFORMATION_MESSAGE);
//            System.out.println(e);
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
