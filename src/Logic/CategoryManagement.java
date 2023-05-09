package Logic;

import Entity.Category;
import DAL.CategoryDAO;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CategoryManagement {
    private ArrayList<Category> CategoryList;
    private CategoryDAO categoryDAO= new CategoryDAO();

    public CategoryManagement() {
        init();
    }
    private void init(){
        CategoryList = categoryDAO.getAll();
    }
    public ArrayList<Category> getCategoryList(){
        return CategoryList;
    }
    public void setCategoryList(ArrayList<Category> CategoryList){
        this.CategoryList = CategoryList;
    }
    public Category findById(int  CategoryId){
        return  categoryDAO.get(CategoryId);
    }
    public Category addCategory(Category category){
        return categoryDAO.create(category);
    }
    public void updateCategory(Category category){
        categoryDAO.update(category);
    }
    public void deleteCategory(Category category){
        categoryDAO.delete(category);
    };
}

