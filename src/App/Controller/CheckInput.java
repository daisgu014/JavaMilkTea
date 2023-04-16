package App.Controller;

import java.util.ArrayList;
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

}
