package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CrudGUI extends JPanel {
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
//    private JButton btnExit;
    private JTable table;
    private String title;

    public CrudGUI( JButton btnAdd, JButton btnUpdate, JButton btnDelete, JTable table, String title) {
        this.btnAdd = btnAdd;
        this.btnUpdate = btnUpdate;
        this.btnDelete = btnDelete;
        this.table = table;
        this.title = title;
    }
//    public CrudGUI( JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title) {
//        this.btnAdd = btnAdd;
//        this.btnUpdate = btnUpdate;
//        this.btnDelete = btnDelete;
//        this.btnExit = btnExit;
//        this.table = table;
//        this.title = title;
//    }
    public CrudGUI(){

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

//    public JButton getBtnExit() {
//        return btnExit;
//    }
//
//    public void setBtnExit(JButton btnExit) {
//        this.btnExit = btnExit;
//    }

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
    public JPanel pnTitle(){
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1250, 120));
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        JLabel jLabel = new JLabel(title,SwingConstants.CENTER);
        jLabel.setFont(new Font("Arial",Font.BOLD,30));
//        jLabel.setBorder(new RoundedBorder(20,Color.MAGENTA));
        jLabel.setBorder(new RoundedBorder(20));
        jLabel.setForeground(Color.blue);
        jLabel.setBackground(Color.lightGray);
        jLabel.setOpaque(true);
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
        JScrollPane sp = new JScrollPane(getTable());
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(1200, 380));
        jPanel.add(sp);
        return jPanel;
    }
    //Vùng Panel chứa các nút điều khiển của Form
    public JPanel pnRemote(){
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1250, 280));
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,55,100));
        getBtnAdd().setPreferredSize(new Dimension(200, 50));
        getBtnAdd().setBorder(new RoundedBorder(20));
//        getBtnAdd().setBackground(Color.CYAN);
        getBtnUpdate().setPreferredSize(new Dimension(200, 50));
        getBtnUpdate().setBorder(new RoundedBorder(20));
        getBtnDelete().setPreferredSize(new Dimension(200, 50));
        getBtnDelete().setBorder(new RoundedBorder(20));
//        getBtnExit().setPreferredSize(new Dimension(200, 50));
//        getBtnExit().setBorder(new RoundedBorder(20));
        double x = 50;
        double y = 50;
        btnAdd.setMixingCutoutShape(new RoundRectangle2D.Double(x, y, 100, 100, 50, 50));
        jPanel.add(getBtnAdd());
        jPanel.add(getBtnUpdate());
        jPanel.add(getBtnDelete());
//        jPanel.add(getBtnExit());
        return jPanel;
    }

    public void Scene(){
        setSize(1250,800);
        setLayout(new FlowLayout());
        add(pnTitle());
        add(pnTable());
        add(pnRemote());
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,1000);

        String[] columns = {"Name", "Designation"}; //, "Salary"
        Object[][] rows = {{"Adithya", "Content Developer", 25000},
                {"Jai", "SME", 30000},
                {"Chaitanya", "Java Engineer", 45000},
                {"Ramesh", "Scala Developer", 40000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000},
                {"Ravi", "SAP  Consultant", 70000}
        };
        JTable jTable = new JTable(rows,columns);
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        JButton exit = new JButton("Exit");
        CrudGUI crudGUI = new CrudGUI(add,edit,delete,jTable,"Account");
        crudGUI.Scene();
        jFrame.add(crudGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}