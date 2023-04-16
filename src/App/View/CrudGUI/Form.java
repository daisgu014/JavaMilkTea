package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    private JLabel lbTitle;
    private JButton btnAccept;
    private JButton btnCancel;

    public Form(JLabel lbTitle, JButton btnAccept, JButton btnCancel){
        this.lbTitle = lbTitle;
        this.btnAccept = btnAccept;
        this.btnCancel = btnCancel;
    }

    public Form(){

    }

    public JLabel getLbTitle() {
        return lbTitle;
    }

    public void setLbTitle(JLabel lbTitle) {
        this.lbTitle = lbTitle;
    }

    public JButton getBtnAccept() {
        return btnAccept;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    public void setBtnAccept(JButton btnAccept) {
        this.btnAccept = btnAccept;
    }

    public JPanel pnTitle(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(590,80));
        getLbTitle().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getLbTitle().setPreferredSize(new Dimension(100,50));
        pn.add(getLbTitle());
        return pn;
    }
    public JPanel pnRemote(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,100));
        pn.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        pn.add(getBtnAccept());
        pn.add(getBtnCancel());
        return pn;
    }
    public JPanel pnPnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(BorderFactory.createLineBorder(Color.red));
        return pn;
    }
    public void SceneForm(){
        setSize(500,600);
        setLayout(new FlowLayout());
        add(pnTitle());
        add(pnPnContainer());
        add(pnRemote());
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        JLabel lbTitle = new JLabel("Hihi",SwingConstants.CENTER);
        JButton btnAccept = new JButton("Accept");
        JButton btnCancel = new JButton("Cancel");
        JPanel pnContainer = new JPanel();
        pnContainer.setPreferredSize(new Dimension(590,200));
        Form form = new Form(lbTitle,btnAccept,btnCancel);
        form.SceneForm();
    }
}
