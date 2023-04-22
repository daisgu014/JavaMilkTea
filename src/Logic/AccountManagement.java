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
    public void Insert(Account account, String empID){
        accountDAO.Insert(account,empID);
    }
    public void Update(Account account){
       try{
           accountDAO.update(account);
       }catch (Exception e){
           System.out.println(e);
       }
    }
    public void Delete(Account account){
        accountDAO.delete(account);
    }
}
