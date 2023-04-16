package App.Model;

import Entity.Account;
import Logic.AccountManagement;

import java.util.HashMap;

public class AccountModel {
    private HashMap<Integer, Account> accounts;
    private final AccountManagement accountManagement = new AccountManagement();

    public AccountModel(){

    }
    public boolean AddAccount(Account account){
        return true ;
    }
    public HashMap<Integer, Account> getDataAccount(){
        accounts = accountManagement.getAccounts();
        return  accounts;
    }
}
