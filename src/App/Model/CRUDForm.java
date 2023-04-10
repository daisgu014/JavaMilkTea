package App.Model;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class CRUDForm extends JFrame{
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnExit;
    private JTable table;
    private String title;

    public CRUDForm(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title) {
        this.btnAdd = btnAdd;
        this.btnUpdate = btnUpdate;
        this.btnDelete = btnDelete;
        this.btnExit = btnExit;
        this.table = table;
        this.title = title;
    }

    public CRUDForm(){

    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

//    public void getFrom(String txt1, String txt2,Object[][] rows, Object[] colums){
//        frame = new JFrame();
//        frame.setSize(1250,800);
//        frame.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        lb1 = new JLabel(txt1,SwingConstants.CENTER);
//        lb1.setFont(new Font("Arial",Font.BOLD,30));
//        lb2 = new JLabel(txt2,SwingConstants.CENTER);
//        lb2.setFont(new Font("Arial",Font.BOLD,30));
//        tb= new JTable(rows,colums);
//        pn = new JPanel();
//        pn.setLayout(null);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.ipadx = 800;
//        gbc.ipady = 100;
//        frame.add(lb1, gbc);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.ipadx = 400;
//        gbc.ipady = 100;
//        frame.add(lb2, gbc);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.ipadx = 800;
//        gbc.ipady = 600;
//        JScrollPane sp = new JScrollPane(tb);
//        frame.add(sp, gbc);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        gbc.ipadx = 400;
//        gbc.ipady = 600;
//        pn.add(this.menuBtn());
//        frame.add(pn, gbc);
//        //test
//        JPanel p2 = new JPanel();
//        p2.setBorder(BorderFactory.createLineBorder(Color.black));
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        gbc.ipady = 100;
//        p2.add(this.menuBtn());
//        frame.add(p2, gbc);
//        //test
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }

    //Vùng Panel chứa Title của Form
    public JPanel pnTitle(){
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1250, 120));
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        JLabel jLabel = new JLabel(title,SwingConstants.CENTER);
        jLabel.setFont(new Font("Arial",Font.BOLD,30));
        jLabel.setBorder(new RoundedBorder(20,Color.MAGENTA));
        jLabel.setPreferredSize(new Dimension(400, 80));
        jLabel.setBounds(425,20,400,80);
        jPanel.add(jLabel);
        return  jPanel;
    }
    //Vùng Panel chứa bảng dữ liệu của Form
    public JPanel pnTable(){
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1250, 400));
        jPanel.setLayout(new FlowLayout());
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(1200, 380));
        jPanel.add(sp);
        return jPanel;
    }
    //Vùng Panel chứa các nút điều khiển của Form
    public JPanel pnRemote(){
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1250, 280));
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,55,100));
        btnAdd.setPreferredSize(new Dimension(200, 50));
        btnAdd.setBorder(new RoundedBorder(20,Color.MAGENTA));
        btnUpdate.setPreferredSize(new Dimension(200, 50));
        btnUpdate.setBorder(new RoundedBorder(20,Color.MAGENTA));
        btnDelete.setPreferredSize(new Dimension(200, 50));
        btnDelete.setBorder(new RoundedBorder(20,Color.MAGENTA));
        btnExit.setPreferredSize(new Dimension(200, 50));
        btnExit.setBorder(new RoundedBorder(20,Color.MAGENTA));
        jPanel.add(btnAdd);
        jPanel.add(btnUpdate);
        jPanel.add(btnDelete);
        jPanel.add(btnExit);
        return jPanel;
    }
    public void SceneCRUD(){
        setSize(1250,800);
        setLayout(new FlowLayout());
        add(pnTitle());
        add(pnTable());
        add(pnRemote());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        String[] columns = {"Name", "Designation"}; //, "Salary"
        Object[][] rows = {{"Adithya", "Content Developer", 25000},
                {"Jai", "SME", 30000},
                {"Chaitanya", "Java Engineer", 45000},
                {"Ramesh", "Scala Developer", 40000},
                {"Ravi", "SAP  Consultant", 70000}};
        JTable jTable = new JTable(rows,columns);
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        JButton exit = new JButton("Exit");
        CRUDForm crud = new CRUDForm(add,edit,delete,exit,jTable,"Account");
//        crud.getFrom("t","t",rows,columns);
        crud.SceneCRUD();
    }
    private static class RoundedBorder implements Border {

        private int radius;
        private Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
