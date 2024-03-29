package App.Controller;

import App.Model.CategoryModel;
import App.View.Shop.loadData;
import Entity.Category;
import Entity.Employee;
import org.apache.poi.ss.formula.functions.DProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CategoryController {
    private CategoryModel categoryModel;
    private ArrayList<Category> categoryArrayList;
    private JTable table;
    public CategoryController(){
        categoryModel = new CategoryModel();

    }
    public ArrayList<Category> getCategoryData(){
        categoryArrayList = categoryModel.getData();
        return categoryArrayList;
    }
    public JTable setDataTable(){
        String[] columns = {"ID","Name", "Create At","Delete At"};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Category category : categoryArrayList) {
            Object[] row = {category.getCategoryID(),category.getCategoryName(),category.getCreateAt(),category.getDeleteAt()};
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
    public Category InsertCategory(Category category){
        Category newCategory = new Category();
        try {
            newCategory = categoryModel.Insert(category);
            JOptionPane.showMessageDialog(null, "Thêm thành công !",
                    "Add Category",JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
            );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "Add Category",JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/cancel.png")
                    );
        }
        return  newCategory;
    }
    public void UpdateCategory(Category category){
        try {
            categoryModel.Update(category);
            for(Category c : loadData.categories){
                if(c.getCategoryID()==category.getCategoryID()){
                    c.setCategoryName(category.getCategoryName());
                }
            }
            JOptionPane.showMessageDialog(null, "Thành công !",
                    "Update Category",JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
                    );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "Update Category",JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/cancel.png")
                    );
        }
    }
    public void DeleteCategory(Category category){
        try {
            categoryModel.Delete(category);
            loadData.categories.removeIf(p->p.getCategoryID()== category.getCategoryID());

            JOptionPane.showMessageDialog(null, "Thành công !",
                    "Delete Category",JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/checked.png")
                    );
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "Delete Category",JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("src/Assets/Icons/cancel.png")
                    );
        }
    }
}
