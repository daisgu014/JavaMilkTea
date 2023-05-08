package App.View.CrudGUI;

import App.Controller.CategoryController;
import App.Model.CategoryModel;
import App.Model.EmployeeModel;
import Entity.Category;
import Entity.WorkPosition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductFormAdd extends FormDialog{
    private JTextField tfProductName;
    private JComboBox cbCategory;
    private JLabel lbImange;
    private JButton btnUpload;

    public ProductFormAdd(JTextField tfProductName, JComboBox cbCategory, JLabel lbImange, JButton btnUpload) {
        this.tfProductName = tfProductName;
        this.cbCategory = cbCategory;
        this.lbImange = lbImange;
        this.btnUpload = btnUpload;
    }
    public ProductFormAdd(){
        SceneForm();
    }

    public JTextField getTfProductName() {
        return tfProductName;
    }

    public void setTfProductName(JTextField tfProductName) {
        this.tfProductName = tfProductName;
    }

    public JComboBox getCbCategory() {
        return cbCategory;
    }

    public void setCbCategory(JComboBox cbCategory) {
        this.cbCategory = cbCategory;
    }

    public JLabel getLbImange() {
        return lbImange;
    }

    public void setLbImange(JLabel lbImange) {
        this.lbImange = lbImange;
    }

    public JButton getBtnUpload() {
        return btnUpload;
    }

    public void setBtnUpload(JButton btnUpload) {
        this.btnUpload = btnUpload;
    }

    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(new RoundedBorder(20));
        JLabel lbName = new JLabel("ProductName: ",SwingConstants.CENTER);
        setTfProductName(new JTextField());
        pn.add(pnRows(lbName,getTfProductName()));
        //lay du lieu category set cho combox
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        CategoryController categoryController = new CategoryController();
        categoryArrayList = categoryController.getCategoryData();
        String[] CategoryList = new String[categoryArrayList.size()];
        for (int i = 0;i < categoryArrayList.size();i++ ){
            CategoryList[i] = categoryArrayList.get(i).getCategoryName();
        }
        setCbCategory(new JComboBox(CategoryList));
        //
        getCbCategory().setPreferredSize(new Dimension(200,30));
        pn.add(getCbCategory());
        JLabel lbImg = new JLabel("Choose Image",SwingConstants.CENTER);
        lbImg.setPreferredSize(new Dimension(400,80));
        lbImg.setHorizontalAlignment(JLabel.CENTER);
        lbImg.setVerticalAlignment(JLabel.CENTER);
        setLbImange(lbImg);
        pn.add(getLbImange());
        btnUpload = new JButton("Upload");
        btnUpload.setPreferredSize(new Dimension(80,20));
        setBtnUpload(btnUpload);
        pn.add(getBtnUpload());
        return pn;
    }
    @Override
    public void SceneForm(){
        setPreferredSize(new Dimension(500,600));
        setLayout(new FlowLayout());
        setLbTitle( new JLabel("Create Product",SwingConstants.CENTER));
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }
}
