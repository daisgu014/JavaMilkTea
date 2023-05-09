package App.View.CrudGUI;

import App.Model.EmployeeModel;
import Entity.WorkPosition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SizeFormUpdate extends FormDialog{
    private JTextField tfSign;
    private JTextField tfDescription;

    public SizeFormUpdate(JTextField tfSign, JTextField tfDescription) {
        this.tfSign = tfSign;
        this.tfDescription = tfDescription;
    }

    public SizeFormUpdate(){
        SceneForm();
    }

    public JTextField getTfSign() {
        return tfSign;
    }

    public void setTfSign(JTextField tfSign) {
        this.tfSign = tfSign;
    }

    public JTextField getTfDescription() {
        return tfDescription;
    }

    public void setTfDescription(JTextField tfDescription) {
        this.tfDescription = tfDescription;
    }

    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(new RoundedBorder(20));
        JLabel lbSign = new JLabel("Sign: ",SwingConstants.CENTER);
        setTfSign(new JTextField());
        pn.add(pnRows(lbSign,getTfSign()));
        JLabel lbDes = new JLabel("Description: ",SwingConstants.CENTER);
        setTfDescription(new JTextField());
        pn.add(pnRows(lbDes,getTfDescription()));
        return pn;
    }
    @Override
    public void SceneForm(){
        setPreferredSize(new Dimension(500,600));
        setLayout(new FlowLayout());
        setLbTitle( new JLabel("Size",SwingConstants.CENTER));
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }

}
