package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDialog extends JPanel {
    private JLabel lbTitle;

    public FormDialog(JLabel lbTitle) {
        this.lbTitle = lbTitle;
    }

    public FormDialog(){
//        SceneForm();
    }

    public JLabel getLbTitle() {
        return lbTitle;
    }

    public void setLbTitle(JLabel lbTitle) {
        this.lbTitle = lbTitle;
    }


    public JPanel pnTitle(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(590,80));
        getLbTitle().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getLbTitle().setPreferredSize(new Dimension(100,50));
        pn.add(getLbTitle());
        return pn;
    }
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(BorderFactory.createLineBorder(Color.red));
        return pn;
    }
    public void SceneForm(){
//        setPreferredSize(new Dimension(500,600));
        setPreferredSize(new Dimension(500,400));
        setLayout(new FlowLayout());
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Test Dialog");
        f.setSize(500,600);
        f.setLayout(null);
        JButton btn = new JButton("Test");
        btn.setBounds(50,50,200,200);
        JLabel lb = new JLabel("Test Dialog");
        FormDialog formDialog = new FormDialog();
        formDialog.setLbTitle(lb);
        formDialog.SceneForm();
        Object[] message = {formDialog};
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(f,message,"Delete Account",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon(""));
                System.out.println(option);
            }
        });
        f.add(btn);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
