package App.Model;

import Entity.Size;
import Logic.SizeManagement;

import java.util.ArrayList;

public class SizeModel {
    private ArrayList<Size> sizeArrayList;
    private SizeManagement sizeManagement ;
    public SizeModel (){
        sizeManagement = new SizeManagement();
    }
    public ArrayList<Size> getData(){
        sizeArrayList = sizeManagement.getSizes();
        return sizeArrayList;
    }
    public void Insert(Size size){
        sizeManagement.Insert(size);
    }
    public void Update(Size size){
        sizeManagement.Update(size);
    }
    public void Delete(Size size){

    }
}
