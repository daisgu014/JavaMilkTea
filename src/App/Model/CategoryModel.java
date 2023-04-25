package App.Model;

import Entity.Category;
import Logic.CategoryManagement;

import java.util.ArrayList;

public class CategoryModel {
    private CategoryManagement categoryManagement;
    public CategoryModel(){
        categoryManagement = new CategoryManagement();
    }
    public ArrayList<Category> getData(){
        return categoryManagement.getCategoryList();
    }
}
