package Logic;

import Entity.Account;
import DAL.AccountDAO;

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

}
