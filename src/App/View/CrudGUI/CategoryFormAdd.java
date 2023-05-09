package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;

public class CategoryFormAdd extends FormDialog{
    private JTextField tfCategoryName;

    public CategoryFormAdd(JTextField tfCategoryName) {
        this.tfCategoryName = tfCategoryName;
    }

    public CategoryFormAdd(){
        SceneForm();
    }

    public JTextField getTfCategoryName() {
        return tfCategoryName;
    }

    public void setTfCategoryName(JTextField tfCategoryName) {
        this.tfCategoryName = tfCategoryName;
    }

    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout(FlowLayout.CENTER));
        pn.setBorder(new RoundedBorder(20));
        JLabel lbName = new JLabel("Category Name: ",SwingConstants.CENTER);
        setTfCategoryName(new JTextField());
        pn.add(pnRows(lbName,getTfCategoryName()));
        return pn;
    }
    @Override
    public void SceneForm(){
        setPreferredSize(new Dimension(500,600));
        setLayout(new FlowLayout());
        setLbTitle( new JLabel("Category",SwingConstants.CENTER));
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }
}
