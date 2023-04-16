package Logic;

import Entity.Account;
import DAL.AccountDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountManagement {
    private HashMap<Integer, Account> accounts;
    private AccountDAO accountDAO = new AccountDAO();
    public AccountManagement(){
        init();
    }
    public void init(){
        accounts=accountDAO.getAccounts();
    }
    public HashMap<Integer,Account> getAccounts(){
        return accounts;
    }
    public Account findByEmployeeID(Integer id){
     return accounts.get(id);
    }
    public boolean Insert(Account account){
        try {
            accountDAO.create(account);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public void Update(Account account){
        accountDAO.update(account);
    }
    public void Delete(Account account){

    }
}
