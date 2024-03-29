package App.View;

import App.Controller.CustomerController;
import Entity.Customer;
import Logic.CustomerManagement;
import org.w3c.dom.CDATASection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import static App.View.Shop.loadData.customers;
public class CustomerGUI extends JPanel{
    private JTable customerTable;
    private JTextField txtCustomerPhone, txtCustomerName, txtCustomerPoint;
    private JLabel labelTitle, labelPhone, labelName, labelPoint;
    private JButton btnClear, btnAdd, btnEdit, btnDelete;
    private JPanel customerTablePanel, customerForm;
    private DefaultTableModel defaultTableModel;
    private JScrollPane scrollPane;

    CustomerManagement customerManagement = new CustomerManagement();
    Integer selectedRow=null;
    public CustomerGUI(){
        setLayout(new BorderLayout());
        setSize(1300,1000);
        /*-------Title Customer----------*/
        labelTitle = new JLabel("Manager Customer");
        labelTitle.setFont(new Font("SF Pro Display", Font.BOLD, 25));
        /*-------Title Phone----------*/
        labelPhone= new JLabel("Phone customer: ");
        labelPhone.setForeground(Color.white);
        labelPhone.setPreferredSize(new Dimension(150,40));
        labelPhone.setBackground(new Color(247, 159, 31));
        labelPhone.setOpaque(true);
        labelPhone.setHorizontalAlignment(0);
        labelPhone.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        /*-------Title Name----------*/
        labelName= new JLabel("Name customer: ");
        labelName.setForeground(Color.white);
        labelName.setPreferredSize(new Dimension(150,40));
        labelName.setBackground(new Color(247, 159, 31));
        labelName.setOpaque(true);
        labelName.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        labelName.setHorizontalAlignment(0);
        /*-------Title Point----------*/
        labelPoint= new JLabel("Point customer: ");
        labelPoint.setForeground(Color.white);
        labelPoint.setPreferredSize(new Dimension(150,40));
        labelPoint.setBackground(new Color(247, 159, 31));
        labelPoint.setOpaque(true);
        labelPoint.setHorizontalAlignment(0);
        labelPoint.setFont(new Font("Arial", Font.BOLD, 16));
        /*-------Clear Button----------*/
        btnClear = new JButton("CLEAR");
        btnClear.setPreferredSize(new Dimension(100,50));
        btnClear.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        btnClear.setForeground(Color.white);
        btnClear.setBackground(new Color(214, 48, 49));
        /*-------TextField Phone----------*/
        txtCustomerPhone = new JTextField(25);
        txtCustomerPhone.setPreferredSize(new Dimension(120,40));
        /*-------TextField Name----------*/
        txtCustomerName = new JTextField(25);
        txtCustomerName.setPreferredSize(new Dimension(120,40));
        /*-------TextField Point----------*/
        txtCustomerPoint = new JTextField(25);
        txtCustomerPoint.setPreferredSize(new Dimension(120,40));
        txtCustomerPoint.setText("0");
        txtCustomerPoint.setEnabled(false);
        /*-------Add Button----------*/
        btnAdd = new JButton("ADD");
        btnAdd.setPreferredSize(new Dimension(100,50));
        btnAdd.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        btnAdd.setForeground(Color.white);
        btnAdd.setBackground(new Color(247, 159, 31));
        /*-------Edit Button----------*/
        btnEdit = new JButton("EDIT");
        btnEdit.setPreferredSize(new Dimension(100,50));
        btnEdit.setFont(new Font("Arial", Font.BOLD, 16));
        btnEdit.setForeground(Color.white);
        /*-------Delete Button----------*/
        btnDelete = new JButton("DELETE");
        btnEdit.setBackground(new Color(247, 159, 31));
        btnDelete.setPreferredSize(new Dimension(100,50));
        btnDelete.setBackground(new Color(247, 159, 31));
        btnDelete.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        btnDelete.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(0);
        /*----Create panel to add labelPanel after this panel add labelPanel*/
        JPanel labelPanel = new JPanel();
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
        jPanel.add(btnClear);
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
        /*-----------Init Table-------------*/
        String[] columnNames ={"Customer Phone","Customer Name", "Point"};
        Object[][] data = new Object[customers.size()][3];
        for(int i=0;i<customers.size();i++){
            data[i][0]=customers.get(i).getPhone();
            data[i][1]=customers.get(i).getCustomerName();
            data[i][2]=customers.get(i).getPoints();
        }
        defaultTableModel = new DefaultTableModel(data,columnNames);
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
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        /*-------------BtnAdd Event----------*/
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertCustomer();
            }
        });
        /*-------------btnClear Event----------*/
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
                btnAdd.setEnabled(true);
            }
        });
        /*-------------Get row is selected ----------*/
        customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    selectedRow = customerTable.getSelectedRow();
                    if(selectedRow!=-1) {
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        Customer customer = CustomerSelected(selectedRow);
                        setDataInput(customer);

                    }

                }
            }
        });
        /*-------------btnEdit Event----------*/
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option =  JOptionPane.showConfirmDialog(null, "Bạn chắc muốn chỉnh sửa?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.out.println(btnEdit.getActionListeners().length);
                    update(selectedRow);
                    clearInput();
                }else{
                    return;
                }
            }
        });
        /*-------------btnDelete Event----------*/
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = CustomerSelected(selectedRow);
                int option =  JOptionPane.showConfirmDialog(null, "Bạn chắc muốn xóa "+customer.getCustomerName()+"?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    delete(customer,selectedRow);
                    clearInput();
                }else {
                    return;
                }
            }
        });
        /*-------------reload Event----------*/
    customerTablePanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            reloadTable();
        }
    });
    }
    /*-------------Return customer with selectedRow----------*/
    public Customer CustomerSelected(Integer selectedRow){
        Object phone = customerTable.getValueAt(selectedRow,0);
        Object name = customerTable.getValueAt(selectedRow,1);
        Object point = customerTable.getValueAt(selectedRow,2);
        return new Customer(String.valueOf(phone),String.valueOf(name),Integer.parseInt(String.valueOf(point)));
    }
    /*-------------Render input value with selectedRow----------*/
    public void setDataInput(Customer customer){
        txtCustomerName.setText(customer.getCustomerName());
        txtCustomerPhone.setText(customer.getPhone());
        txtCustomerPoint.setText(String.valueOf(customer.getPoints()));

    }
    /*--------------Check Customer exited----------------------*/
    public boolean checkCustomer(Customer customer){
        for(Customer o : customers){
            if(o==customer){
                return  false;
            }
        }
        return true;
    }
    /*--------------Check Customer Phone----------------------*/
    public boolean checkPhone(String phone){
        for(Customer o : customers){
            if(o.getPhone().equalsIgnoreCase(phone.trim())){
                return  false;
            }
        }
        return true;
    }
    /*--------------Clear Input---------------------*/
    public void clearInput(){
        txtCustomerName.setText("");
        txtCustomerPhone.setText("");
        txtCustomerPoint.setText("0");
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    /*--------------Add New Customer---------------------*/
    public void insertCustomer(){
        String name = txtCustomerName.getText();
        String phone =txtCustomerPhone.getText();
        Integer point = Integer.parseInt(txtCustomerPoint.getText());
        Customer customer = null;
        if(!name.equalsIgnoreCase("") && !phone.equalsIgnoreCase("") &&point>=0){
            customer= new Customer(phone,name,point);
            if(checkCustomer(customer)){
                if(checkPhone(phone)){
                    customers.add(customer);
                    customerManagement.create(customer);
                    defaultTableModel.addRow(customer.toObject());
                    clearInput();
                }else {
                    JOptionPane.showMessageDialog(null,"Số điện thoại đã tồn tại!!!");
                }
            }else {
                JOptionPane.showMessageDialog(null,"Khách hàng đã tồn tại!!!");
            }

        }else{
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin");
        }
    }
    /*--------------Update New Customer---------------------*/
    public void update(Integer selected){
        String name = txtCustomerName.getText();
        String phone =txtCustomerPhone.getText();
        Integer point = Integer.parseInt(txtCustomerPoint.getText());
        if(!checkPhone(phone)){
            Customer customer = new Customer(phone,name,point);
            for(Customer o : customers){
                if(o.getPhone().equalsIgnoreCase(phone)){
                    o.setCustomerName(name);
                    o.setPoints(point);
                }
            }
            customerManagement.update(customer);
            defaultTableModel.setValueAt(name,selected,1);
            defaultTableModel.setValueAt(point,selected,2);
        }
    }
    /*--------------Delete New Customer---------------------*/
    public void delete(Customer customer, Integer row){
        customerManagement.delete(customer);
        defaultTableModel.removeRow(row);
        customers.remove(customer);
        defaultTableModel.fireTableDataChanged();
        scrollPane.validate();
    }
    public void reloadTable(){
        String[] columnNames ={"Customer Phone","Customer Name", "Point"};
        Object[][] data = new Object[customers.size()][3];
        for(int i=0;i<customers.size();i++){
            data[i][0]=customers.get(i).getPhone();
            data[i][1]=customers.get(i).getCustomerName();
            data[i][2]=customers.get(i).getPoints();
        }
        defaultTableModel.setDataVector(data,columnNames);
        scrollPane.validate();
        repaint();
        revalidate();
    }
}
