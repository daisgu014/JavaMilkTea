package App.Model;

import DAL.AccountDAO;
import Entity.Account;
import Logic.AccountManagement;

import java.sql.SQLException;
import java.util.HashMap;

public class AccountModel {
    private HashMap<Integer, Account> accounts;
    private final AccountManagement accountManagement = new AccountManagement();

    public AccountModel(){

    }
    public void InsertAccount(Account account,String empID){
        accountManagement.Insert(account,empID);
    }
    public void UpdateAccount(Account account){
        accountManagement.Update(account);
    }
    public void DeleteAccount(Account account){
        accountManagement.Delete(account);
    }
    public HashMap<Integer, Account> getDataAccount(){
        accounts = accountManagement.getAccounts();
        return  accounts;
    }
}
