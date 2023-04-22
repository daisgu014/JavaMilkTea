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
    private void Insert(Size size){

    }
    private void Update(Size size){

    }
    private void Delete(Size size){

    }
}
