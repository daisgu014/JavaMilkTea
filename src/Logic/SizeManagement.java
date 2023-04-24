package Logic;

import Entity.Size;
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
    public void Insert(Size size){
        sizeDAO.Insert(size);
    }
    public void Update(Size size){
        sizeDAO.update(size);
    }
    public void Delete(Size size){
        sizeDAO.delete(size);
    }
}
