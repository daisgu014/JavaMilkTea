package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;

public class PositionFormUpdate extends FormDialog {
    private JTextField tfId;
    private JTextField tfName;
    private JTextField tfLevel;

    public PositionFormUpdate(JTextField tfId ,JTextField tfName, JTextField tfLevel) {
        this.tfName = tfName;
        this.tfLevel = tfLevel;
        this.tfId = tfId;
    }

    public PositionFormUpdate(){
        SceneForm();
    }

    public JTextField getTfId() {
        return tfId;
    }

    public void setTfId(JTextField tfId) {
        this.tfId = tfId;
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
        JLabel lbId = new JLabel("ID: ",SwingConstants.CENTER);
        setTfId(new JTextField());
        pn.add(pnRows(lbId,getTfId()));
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
