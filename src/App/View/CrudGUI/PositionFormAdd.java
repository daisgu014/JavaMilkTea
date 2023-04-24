package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;

public class PositionFormAdd extends FormDialog{
    private JTextField tfName;
    private JTextField tfLevel;

    public PositionFormAdd(JTextField tfName, JTextField tfLevel) {
        this.tfName = tfName;
        this.tfLevel = tfLevel;
    }

    public PositionFormAdd(){
        SceneForm();
    }

    public JTextField getTfName() {
        return tfName;
    }

    public void setTfName(JTextField tfName) {
        this.tfName = tfName;
    }

    public JTextField getTfLevel() {
        return tfLevel;
    }

    public void setTfLevel(JTextField tfLevel) {
        this.tfLevel = tfLevel;
    }

    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel lbName = new JLabel("Name: ",SwingConstants.CENTER);
        setTfName(new JTextField());
        pn.add(pnRows(lbName,getTfName()));
        JLabel lbLevel = new JLabel("Level: ",SwingConstants.CENTER);
        setTfLevel( new JTextField());
        pn.add(pnRows(lbLevel,getTfLevel()));
        return pn;
    }
    @Override
    public void SceneForm(){
        setPreferredSize(new Dimension(500,600));
        setLayout(new FlowLayout());
        setLbTitle( new JLabel("Create WorkPosition",SwingConstants.CENTER));
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }
}
