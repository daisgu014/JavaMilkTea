package DAL;

import Entity.Account;

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
            PreparedStatement prSt = database.getPreStmt("Select * from account WHERE DeleteAt is null");
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
    public Account create(Account account) {
        return null;
    }
    @Override
    public void update(Account account) {
        try {
            PreparedStatement prSt = database.getPreStmt("UPDATE account SET AccountPassword = ? WHERE AccountUsername = ?;");
            prSt.setString(2,account.getUsername());
            prSt.setString(1,account.getPassword());
            prSt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Account account) {
        try {
            PreparedStatement prSt = database.getPreStmt("UPDATE account SET DeleteAt = CURDATE() WHERE AccountUserName = ?");
            prSt.setString(1,account.getUsername());
            prSt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {

    }
    public HashMap<Integer,Account> getAccounts(){
        HashMap<Integer, Account> accounts =new HashMap<>();
        Statement statement = database.getStmt();
        try {
            ResultSet rs= statement.executeQuery("select * from Account  WHERE DeleteAt is null ");
            while (rs.next()){
                accounts.put(rs.getInt(3),
                        new Account(rs.getString(1),rs.getString(2)));
                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }
    public void Insert(Account account,String empID){
        try {
            PreparedStatement prSt = database.getPreStmt("INSERT INTO account(AccountUsername,AccountPassword,EmployeeID) VALUES (?,?,?);");
            prSt.setString(1,account.getUsername());
            prSt.setString(2,account.getPassword());
            prSt.setInt(3, Integer.parseInt(empID));
            prSt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
