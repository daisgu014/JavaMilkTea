package App.View;

import Entity.Customer;
import org.w3c.dom.CDATASection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;

import static App.View.Shop.loadData.customers;
public class CustomerGUI extends JPanel {
    private JTable customerTable;
    private JTextField txtCustomerPhone, txtCustomerName, txtCustomerPoint;
    private JLabel labelTitle, labelPhone, labelName, labelPoint;
    private JButton btnBack, btnAdd, btnEdit, btnDelete;
    private JPanel customerTablePanel, customerForm;
    private DefaultTableModel defaultTableModel;
    private JScrollPane scrollPane;

    public CustomerGUI(){
        setLayout(new BorderLayout());
        setSize(1300,1000);
        labelTitle = new JLabel("Manager Customer");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 25));
        labelPhone= new JLabel("Phone customer: ");
        labelPhone.setForeground(Color.white);
        labelPhone.setPreferredSize(new Dimension(150,40));
        labelPhone.setBackground(new Color(247, 159, 31));
        labelPhone.setOpaque(true);
        labelPhone.setHorizontalAlignment(0);
        labelPhone.setFont(new Font("Arial", Font.BOLD, 16));
        labelName= new JLabel("Name customer: ");
        labelName.setForeground(Color.white);
        labelName.setPreferredSize(new Dimension(150,40));
        labelName.setBackground(new Color(247, 159, 31));
        labelName.setOpaque(true);
        labelName.setFont(new Font("Arial", Font.BOLD, 16));
        labelName.setHorizontalAlignment(0);
        labelPoint= new JLabel("Point customer: ");
        labelPoint.setForeground(Color.white);
        labelPoint.setPreferredSize(new Dimension(150,40));
        labelPoint.setBackground(new Color(247, 159, 31));
        labelPoint.setOpaque(true);
        labelPoint.setHorizontalAlignment(0);
        labelPoint.setFont(new Font("Arial", Font.BOLD, 16));

        txtCustomerPhone = new JTextField(25);
        txtCustomerPhone.setPreferredSize(new Dimension(120,40));
        txtCustomerName = new JTextField(25);
        txtCustomerName.setPreferredSize(new Dimension(120,40));
        txtCustomerPoint = new JTextField(25);
        txtCustomerPoint.setPreferredSize(new Dimension(120,40));
        btnAdd = new JButton("ADD");
        btnAdd.setPreferredSize(new Dimension(100,50));
        btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
        btnAdd.setForeground(Color.white);
        btnAdd.setBackground(new Color(247, 159, 31));
        btnEdit = new JButton("EDIT");
        btnEdit.setPreferredSize(new Dimension(100,50));
        btnEdit.setFont(new Font("Arial", Font.BOLD, 16));
        btnEdit.setForeground(Color.white);
        btnDelete = new JButton("DELETE");
        btnEdit.setBackground(new Color(247, 159, 31));
        btnDelete.setPreferredSize(new Dimension(100,50));
        btnBack = new JButton("BACK");
        btnDelete.setBackground(new Color(247, 159, 31));
        JPanel labelPanel = new JPanel();
        btnDelete.setFont(new Font("Arial", Font.BOLD, 16));
        btnDelete.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(0);
        labelPanel.setPreferredSize(new Dimension(50,50));
        labelPanel.add(labelTitle,BorderLayout.CENTER);
        add(labelPanel, BorderLayout.NORTH);
        customerTablePanel = new JPanel();
        customerForm = new JPanel(new BorderLayout());
        customerForm.setPreferredSize(new Dimension(500,1000));
        customerTablePanel.setBackground(Color.ORANGE);
        customerTablePanel.setPreferredSize(new Dimension(800,1000));
        JPanel form = new JPanel();
        add(customerTablePanel,BorderLayout.CENTER);
        JPanel jPanel = new JPanel();
        jPanel.add(labelName);
        jPanel.add(txtCustomerName);
        jPanel.add(labelPhone);
        jPanel.add(txtCustomerPhone);
        jPanel.add(labelPoint);
        jPanel.add(txtCustomerPoint);
        jPanel.setPreferredSize(new Dimension(500,100));
        customerForm.setBackground(Color.blue);
        JPanel newPane = new JPanel();
        newPane.setPreferredSize(new Dimension(500,100));
        customerForm.add(newPane,BorderLayout.NORTH);
        customerForm.add(jPanel,BorderLayout.CENTER);
        JPanel btnCustomer = new JPanel();
        btnCustomer.setPreferredSize(new Dimension(500,300));
        btnCustomer.add(btnAdd);
        btnCustomer.add(btnEdit);
        btnCustomer.add(btnDelete);
        customerForm.add(btnCustomer,BorderLayout.SOUTH);
        add(customerForm,BorderLayout.EAST);
        String[] columnNames ={"Customer Phone","Customer Name", "Point"};
        Object[][] data = new Object[customers.size()][3];
        for(int i=0;i<customers.size();i++){
            data[i][0]=customers.get(i).getPhone();
            data[i][1]=customers.get(i).getCustomerName();
            data[i][2]=customers.get(i).getPoints();
        }
        defaultTableModel = new DefaultTableModel(data,columnNames);
        defaultTableModel.addRow(new Object[]{"01011","Nguyễn Hữu Đại","1000"});
        customerTable= new JTable(defaultTableModel);
        TableColumn column1 = customerTable.getColumnModel().getColumn(0);
        column1.setPreferredWidth(200);
        TableColumn column2 = customerTable.getColumnModel().getColumn(1);
        column2.setPreferredWidth(200);
        TableColumn column3 = customerTable.getColumnModel().getColumn(2);
        column3.setPreferredWidth(200);
         scrollPane = new JScrollPane(customerTable);
        customerTablePanel.add(scrollPane);
        add(customerTablePanel,BorderLayout.CENTER);



    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1300,1000);
        jFrame.getContentPane().add(new CustomerGUI());
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
