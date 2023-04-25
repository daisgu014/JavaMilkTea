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
    public Category Insert(Category category){
        return categoryManagement.addCategory(category);
    }
    public void Update(Category category){
        categoryManagement.updateCategory(category);
    }
    public void Delete(Category category){
        categoryManagement.deleteCategory(category);
    }
}
