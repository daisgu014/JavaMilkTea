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
        getLbTitle().setBorder(BorderFactory.createLineBorder(new Color(247, 159, 31),2));
        getLbTitle().setForeground(new Color(247, 159, 31));
        getLbTitle().setBackground(Color.decode("#F7D8C6"));
        getLbTitle().setFont(new Font("Arial", Font.BOLD, 20));
        getLbTitle().setPreferredSize(new Dimension(200,50));
        getLbTitle().setOpaque(true);
        pn.add(getLbTitle());
        return pn;
    }
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(BorderFactory.createLineBorder(Color.decode("#1CA7EC")));
        pn.setBackground(Color.decode("#9AD9EA"));
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
    public JPanel pnRows(JLabel jLabel, JTextField textField){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(480,50));
        jLabel.setPreferredSize(new Dimension(200,30));
        textField.setPreferredSize(new Dimension(200,30));
        pn.add(jLabel);
        pn.add(textField);
        return pn;
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
