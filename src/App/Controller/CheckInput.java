package App.Controller;

import Entity.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput<T> {
    public boolean checkPhone(String str){
        Pattern ptrn = Pattern.compile("\\d{10}");
        Matcher match = ptrn.matcher(str);
        return match.find() && match.group().equals(str);
    }
    public boolean checkUsername(String str){
        Pattern ptrn = Pattern.compile("[a-z0-9_-]{6,12}$");
        Matcher match = ptrn.matcher(str);
        return match.find() && match.group().equals(str);
    }
    public boolean checkPasword(String str){
        Pattern ptrn = Pattern.compile("[a-z0-9_-]{6,12}$");
        Matcher match = ptrn.matcher(str);
        return match.find() && match.group().equals(str);
    }
    public boolean checkActiveMap(HashMap<Integer,T> map,Integer string){
        for (Map.Entry<Integer,T> set: map.entrySet()){
            if (set.getKey()==string){
                return true;
            }
        }
        return false;
    }
    public boolean checkNumber(String str){
        Pattern ptrn = Pattern.compile("[0-9]");
        Matcher match = ptrn.matcher(str);
        return match.find() && match.group().equals(str);
    }
    public static void main(String[] args) {
        CheckInput checkInput = new CheckInput();
        System.out.println(checkInput.checkNumber("88"));
    }
}
