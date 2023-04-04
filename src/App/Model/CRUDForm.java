package App.Model;

import javax.swing.*;
import java.awt.*;



public class CRUDForm {
    private JFrame frame;
    private JLabel lb1;
    private JLabel lb2;
    private JTable tb;
    private JPanel pn;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnExit;

    public CRUDForm(JFrame frame, JLabel lb1, JLabel lb2, JTable tb, JPanel pn, JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit) {
        this.frame = frame;
        this.lb1 = lb1;
        this.lb2 = lb2;
        this.tb = tb;
        this.pn = pn;
        this.btnAdd = btnAdd;
        this.btnUpdate = btnUpdate;
        this.btnDelete = btnDelete;
        this.btnExit = btnExit;
    }
    public CRUDForm(){

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getLb1() {
        return lb1;
    }

    public void setLb1(JLabel lb1) {
        this.lb1 = lb1;
    }

    public JLabel getLb2() {
        return lb2;
    }

    public void setLb2(JLabel lb2) {
        this.lb2 = lb2;
    }

    public JTable getTb() {
        return tb;
    }

    public void setTb(JTable tb) {
        this.tb = tb;
    }

    public JPanel getPn() {
        return pn;
    }

    public void setPn(JPanel pn) {
        this.pn = pn;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
        this.btnExit = btnExit;
    }

    public void getFrom(String txt1, String txt2,Object[][] rows, Object[] colums){
        frame = new JFrame();
        frame.setSize(1250,800);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        lb1 = new JLabel(txt1,SwingConstants.CENTER);
        lb1.setFont(new Font("Arial",Font.BOLD,30));
        lb2 = new JLabel(txt2,SwingConstants.CENTER);
        lb2.setFont(new Font("Arial",Font.BOLD,30));
        tb= new JTable(rows,colums);
        pn = new JPanel();
        pn.setLayout(null);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 800;
        gbc.ipady = 100;
        frame.add(lb1, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 400;
        gbc.ipady = 100;
        frame.add(lb2, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 800;
        gbc.ipady = 700;
        JScrollPane sp = new JScrollPane(tb);
        frame.add(sp, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 400;
        gbc.ipady = 700;
        pn.add(this.menuBtn());
        frame.add(pn, gbc);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public JPanel menuBtn(){
        JPanel pnMenu = new JPanel();
        pnMenu.setBounds(0,500,400,60);
        pnMenu.setLayout(null);
//        pnMenu.setBorder(BorderFactory.createLineBorder(Color.green));
        btnAdd = new JButton("Add");
        btnAdd.setBounds(10,0,90,50);
//        btnAdd.setSize(85,60);
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(110,0,90,50);
//        btnUpdate.setSize(85,60);
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(210,0,90,50);
//        btnDelete.setSize(85,60);
        btnExit = new JButton("Exit");
        btnExit.setBounds(310,0,90,50);
//        btnExit.setSize(85,60);
        pnMenu.add(btnAdd);
        pnMenu.add(btnUpdate);
        pnMenu.add(btnDelete);
        pnMenu.add(btnExit);
        return pnMenu;
    }

    public static void main(String[] args) {
        CRUDForm crud = new CRUDForm();
        Object columns[] = {"Name", "Designation", "Salary"};
        Object rows[][] = {{"Adithya", "Content Developer", 25000},
                {"Jai", "SME", 30000},
                {"Chaitanya", "Java Engineer", 45000},
                {"Ramesh", "Scala Developer", 40000},
                {"Ravi", "SAP  Consultant", 70000}};
        crud.getFrom("Account","Detail",rows,columns);
    }
}
