package App.Controller;

import App.Model.CategoryModel;
import Entity.Category;

import java.util.ArrayList;

public class CategoryController {
    private CategoryModel categoryModel;
    private ArrayList<Category> categoryArrayList;
    public CategoryController(){
        categoryModel = new CategoryModel();

    }
    public ArrayList<Category> getCategoryData(){
        categoryArrayList = categoryModel.getData();
        return categoryArrayList;
    }
}
