package App.Controller;

import App.Model.AccountModel;
import Entity.Account;
import Logic.AccountManagement;

import java.util.HashMap;

public class AccountController {
    private HashMap<Integer, Account> accounts;
    private final AccountModel accountModel = new AccountModel();
    public HashMap<Integer, Account> getDataAccount(){
        accounts = accountModel.getDataAccount();
        return  accounts;
    }
}
