package Logic;

import App.Model.Size;
import DAL.SizeDAO;

import java.util.ArrayList;

public class SizeManagement {
    private ArrayList<Size> sizes;
    private SizeDAO sizeDAO = new SizeDAO();
    public SizeManagement(){
        init();
    }
    public void init(){
        sizes=sizeDAO.getAll();
    }
    public ArrayList<Size> getSizes(){
        return sizes;
    }
    public Size findByName(String nameSize){
         return  sizeDAO.findByName(nameSize);
    }
}
