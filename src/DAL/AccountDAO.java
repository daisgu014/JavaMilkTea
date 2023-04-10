package DAL;

import App.Model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountDAO extends DAO<Account>{
    Database database = new Database();
    ArrayList<Account> accountlist = new ArrayList<>();
    @Override
    public ArrayList<Account> getAll() {
        try {
            PreparedStatement prSt = database.getPreStmt("Select * from account");
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                Account accTb = new Account(rs.getString(1),
                        rs.getString(2));
                accountlist.add(accTb);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountlist;
    }

    @Override
    public Account get(int id) {
        return null;
    }

    @Override
    public int create(Account account) {
        return 0;
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public void deleteById(int id) {

    }
    public HashMap<Integer,Account> getAccounts(){
        HashMap<Integer, Account> accounts =new HashMap<>();
        Statement statement = database.getStmt();
        try {
            ResultSet rs= statement.executeQuery("select * from Account");
            while (rs.next()){
                accounts.put(rs.getInt(3),
                        new Account(rs.getString(1),rs.getString(2)));
                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }
}
