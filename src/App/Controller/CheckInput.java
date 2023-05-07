package App.Controller;

import Entity.Account;
import Entity.Category;
import Entity.Product;
import Entity.ProductSize;

import javax.sound.midi.MidiFileFormat;
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
    public boolean checkActiveMap(HashMap<Integer,T> map,Integer integer){
        for (Map.Entry<Integer,T> set: map.entrySet()){
            if (set.getKey()==integer){
                return true;
            }
        }
        return false;
    }
    public boolean checkNumber(String str){
        Pattern ptrn = Pattern.compile("[0-9]{1,10000000}");
        Matcher match = ptrn.matcher(str);
        return match.find() && match.group().equals(str);
    }
    public boolean  checkActiveProdSize(Integer productID,String size ,ArrayList<Product> productArrayList){
        for (Product prod : productArrayList){
            if (productID == prod.getProductId()){
                for (ProductSize prodsize : prod.getProductSizes() ){
                    if (size.equals(prodsize.getSize())==true){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //Kiểm tra sự tồn tại của Thể loại sản phẩm
    public boolean checkActiveCategory(ArrayList<Category> categoryArrayList,String CategoryName){
        for (Category category : categoryArrayList){
            if (CategoryName.equals(category.getCategoryName())){
                return true;
            }
        }
        return false;
    }
    public  boolean checkActiveProduct(ArrayList<Product> productArrayList, String ProductName ){
        for (Product product : productArrayList){
            if (ProductName.equals(product.getProductName())){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        CheckInput checkInput = new CheckInput();
        System.out.println(checkInput.checkNumber(""));
    }
}
