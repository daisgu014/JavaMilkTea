package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;

public class CategoryFormUpdate extends FormDialog{
    private JTextField tfCategoryName;
    private JTextField tfCategoryId;
    public CategoryFormUpdate(JTextField tfCategoryId,JTextField tfCategoryName) {
        this.tfCategoryId  = tfCategoryId;
        this.tfCategoryName = tfCategoryName;
    }

    public CategoryFormUpdate(){
        SceneForm();
    }

    public JTextField getTfCategoryName() {
        return tfCategoryName;
    }

    public JTextField getTfCategoryId() {
        return tfCategoryId;
    }

    public void setTfCategoryId(JTextField tfCategoryId) {
        this.tfCategoryId = tfCategoryId;
    }

    public void setTfCategoryName(JTextField tfCategoryName) {
        this.tfCategoryName = tfCategoryName;
    }

    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout(FlowLayout.CENTER));
        pn.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel lbId = new JLabel("Category ID: ",SwingConstants.CENTER);
        setTfCategoryId(new JTextField());
        pn.add(pnRows(lbId,getTfCategoryId()));
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
