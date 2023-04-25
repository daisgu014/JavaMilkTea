package App.View.CrudGUI;

import App.Controller.CategoryController;
import App.Controller.ProductController;
import App.Model.ProductTable;
import Entity.Category;
import Entity.Product;
import Entity.Size;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CategoryGUI extends CrudGUI{
    private ArrayList<Category> categoryArrayList;
    private CategoryController categoryController;
    private int index;

    public CategoryGUI(){
        categoryController = new CategoryController();
        getCategoryData();
        setProductScene();
        Scene();
    }
    public void getCategoryData(){
        categoryArrayList = categoryController.getCategoryData();
    }
    public void setProductScene(){
        setTitle("Category");
        setDataTable();
        setButton();
    }
    public void setDataTable(){
        JTable table = categoryController.setDataTable();
        setTable(table);
    }
    public void setButton(){
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        JButton exit = new JButton("Exit");
        index = -1;
        getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = getTable().getSelectedRow();
//                String selectedValue = String.valueOf(getTable().getValueAt(selectedRow, 0));
//                index = Integer.parseInt(selectedValue);
                index = selectedRow;
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryFormAdd categoryFormAdd = new CategoryFormAdd();
                Object[] message = {categoryFormAdd};
                JButton btnAccept = new JButton("Add");
                JButton btnCancel = new JButton("Cancel");
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(categoryFormAdd.getTfCategoryName().getText().trim().equals("")==false){
                            Category category = new Category(null,categoryFormAdd.getTfCategoryName().getText(),null,null);
                            Category newCategory = new Category();
                            newCategory = categoryController.InsertCategory(category);
                            categoryArrayList.add(newCategory);
                            DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                            model.addRow(new Object[]{newCategory.getCategoryID(),newCategory.getCategoryName(),newCategory.getCreateAt(),newCategory.getDeleteAt()});
                            JOptionPane.getRootFrame().dispose();
                        }else {
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin!",
                                    "Update Category", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.getRootFrame().dispose(); // Close the dialog
                    }
                });
                Object[] options = {btnAccept,btnCancel};
                int check = JOptionPane.showOptionDialog(null, message, "Update Category",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                        options, options[0]);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index != -1){
                    CategoryFormUpdate categoryFormUpdate = new CategoryFormUpdate();
                    categoryFormUpdate.getTfCategoryId().setText(String.valueOf(getTable().getValueAt(index,0)));
                    categoryFormUpdate.getTfCategoryId().setEditable(false);
                    categoryFormUpdate.getTfCategoryName().setText(String.valueOf(getTable().getValueAt(index,1)));
                    Object[] message = {categoryFormUpdate};
                    JButton btnAccept = new JButton("Update");
                    JButton btnCancel = new JButton("Cancel");
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (categoryFormUpdate.getTfCategoryName().getText().trim().equals("")==false){
                                Category category = new Category(Integer.parseInt(categoryFormUpdate.getTfCategoryId().getText()),categoryFormUpdate.getTfCategoryName().getText(), null,null);
                                categoryController.UpdateCategory(category);
                                getTable().setValueAt(category.getCategoryName(),index,1);
                            }else {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin !",
                                        "Update Category", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });

                    btnCancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.getRootFrame().dispose(); // Close the dialog
                        }
                    });
                    Object[] options = {btnAccept,btnCancel};
                    int check = JOptionPane.showOptionDialog(null, message, "Update Category",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                            options, options[0]);
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Thể loại sản phẩm !",
                            "Update Category", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Delete Category",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (input == JOptionPane.OK_OPTION){
                    Category category = new Category(Integer.parseInt(String.valueOf(getTable().getValueAt(index,0))),String.valueOf(getTable().getValueAt(index,1)),null,null);
                    categoryController.DeleteCategory(category);
                    ((DefaultTableModel)getTable().getModel()).removeRow(index);
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
        setBtnExit(exit);
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        CategoryGUI categoryGUI = new CategoryGUI();
        jFrame.add(categoryGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
