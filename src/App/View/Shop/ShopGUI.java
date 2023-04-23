package App.View.Shop;

import App.View.Shop.Controller.OrderController;
import App.View.Shop.model.OrderDetailsModel;
import Entity.Category;
import Entity.Customer;
import Entity.OrderDetail;
import Entity.Product;
import Logic.CustomerManagement;
import Logic.Management;
import Logic.ProductManagement;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static App.View.Shop.loadData.customers;
import static App.View.Shop.loadData.orderDetails;

public class ShopGUI extends JPanel {
    static OrderController orderController = new OrderController();
    private JTextField  txtSearch;
    private JButton btnSearch,btnEdit, btnDelete,btnCustomer, btnCloseCustomer,btnClosePoint;
    private JPanel searchBox;
    private JPanel searchPanel;
    private JTable cartTable;
    private JLabel labelPricePoint, labelPrice, labelInfo;

    public JTable getCartTable() {
        return cartTable;
    }

    public void setCartTable(JTable cartTable) {
        this.cartTable = cartTable;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public DefaultTableModel getCartTableModel() {
        return cartTableModel;
    }

    public JScrollPane getCartScrollPane() {
        return cartScrollPane;
    }
    private JPanel productListPanel,customerGirdPanel,customerPanel,customerPointPanel;
    private DefaultTableModel cartTableModel;
    private JScrollPane scrollPane;
    private JScrollPane cartScrollPane;
    private JButton btnOrder, btnClear;
    private JComboBox<String> customerNameList, customerPointList;
    OrderDetail orderDetail = null;
    Integer selectRow = null;
    Management management = new Management();
    public ShopGUI(){
        setLayout(new BorderLayout());
        productListPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        productListPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        render(loadData.products);
        scrollPane = new JScrollPane(productListPanel);
        scrollPane.setPreferredSize(new Dimension(1080, 800));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane,BorderLayout.CENTER);
        JPanel categoryPanel = new JPanel(new GridLayout(0,1));
        categoryPanel.setPreferredSize(new Dimension(300, 800));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        loadData.categories.forEach(cate->
        {
            ArrayList<Product> products = new ArrayList<>();
            String category =cate.getCategoryName();
            JButton jButton = new JButton(category);
            categoryPanel.add(jButton);
            jButton.addActionListener(event->{
                products.clear();
                for(Product o : loadData.products){
                    if(management.getCategoryManagement().findById(o.getCategory()).getCategoryName().equalsIgnoreCase(category)){
                        products.add(o);
                    }
                }
                System.out.println(products.size());
                render(products);
                scrollPane.validate();
            });
        });
        JPanel orderTable = new JPanel(new BorderLayout());
        ArrayList<OrderDetailsModel> orderDetailsModels = orderController.getOrderDetailsModels(orderDetails);
        Object[][] data = new Object[orderDetailsModels.size()][5];
        for(int i=0;i<orderDetailsModels.size();i++){
            data[i][0]=i+1;
            data[i][1]=orderDetailsModels.get(i).getProduct();
            data[i][2]=orderDetailsModels.get(i).getSize();
            data[i][3]=orderDetailsModels.get(i).getQty();
            data[i][4]=orderDetailsModels.get(i).getTotalPrice();
        }

        Object [] columnName = {"STT","Sản phẩm","Size","Số lượng","Giá"};
        cartTableModel = new DefaultTableModel(data,columnName);
        cartTable=new JTable(cartTableModel);
        cartScrollPane = new JScrollPane(cartTable);
        TableColumn column1 = cartTable.getColumnModel().getColumn(1);
        column1.setPreferredWidth(300);
        JPanel btnPanel = new JPanel();
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.setPreferredSize(new Dimension(200,70));
        JPanel RightPanel = new JPanel(new GridLayout(0,1));
        orderTable.setBackground(Color.ORANGE);
        orderTable.add(cartScrollPane,BorderLayout.CENTER);
        orderTable.add(btnPanel,BorderLayout.SOUTH);
        customerPanel = new JPanel();
        btnCustomer = new JButton("Customer");
        customerPanel.add(btnCustomer);
        JPanel orderBtnPanel = new JPanel();
        btnOrder = new JButton("ORDER");
        btnClear = new JButton("CLEAR");
        orderBtnPanel.add(btnOrder);
        orderBtnPanel.add(btnClear);
        add(categoryPanel,BorderLayout.WEST);
        customerGirdPanel = new JPanel(new GridLayout(0,1));
        customerGirdPanel.add(customerPanel);
        customerPointPanel = new JPanel();
        customerGirdPanel.add(customerPointPanel);
        RightPanel.add(orderTable);
        RightPanel.add(customerGirdPanel);
        RightPanel.add(orderBtnPanel);
        add(RightPanel,BorderLayout.EAST);
        searchPanel = new JPanel();
        searchBox = new JPanel();
        btnSearch= new JButton("Search");
        txtSearch = new JTextField(40);
        txtSearch.setPreferredSize(new Dimension(400,50));
        btnSearch.setPreferredSize(new Dimension(100,50));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 18));
        searchBox.setFont(new Font("Arial", Font.PLAIN, 18));
        btnSearch.setBackground(new Color(250, 152, 58));
        searchBox.add(txtSearch,BorderLayout.CENTER);
        searchBox.add(btnSearch,BorderLayout.CENTER);
        searchPanel.add(searchBox,BorderLayout.CENTER);
        searchBox.setPreferredSize(new Dimension(600,60));
        searchBox.setBackground(new Color(250, 211, 144));
        searchPanel.setPreferredSize(new Dimension(700,70));
        add(searchPanel,BorderLayout.NORTH);
        searchProduct();
        cartTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    if( cartTable.getSelectedRow()!=-1) {
                        selectRow= cartTable.getSelectedRow();
                        orderDetail=orderDetailSelected(selectRow);
                    }

                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(orderDetail== null && selectRow==-1){
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn sản phẩm cần xóa");
                }else {
                    int option =  JOptionPane.showConfirmDialog(null, "Bạn chắc muốn xóa "+orderDetail.getProduct().getProductName()+"?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        delete(orderDetail,selectRow);
                    }else {
                        return;
                    }

                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(orderDetail==null && selectRow==-1){
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn sản phẩm cần chỉnh sửa");
                }else{
                    renderProductEditDialog();
                }
            }
        });
        btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    ArrayList<String> customerNameListString = new ArrayList<>();
                    for(Customer s: customers){
                        customerNameListString.add(s.getCustomerName());
                    };
                    String [] cbName = customerNameListString.toArray(new String[0]);
                    customerNameList = new JComboBox<>(cbName);
                    customerNameList.setSelectedItem(cbName[0]);
                    btnCloseCustomer = new JButton("x");
                    customerPanel.add(customerNameList);
                    customerPanel.add(btnCloseCustomer);
                    customerPanel.repaint();
                    repaint();
                    revalidate();
                    btnCustomer.setEnabled(false);
                    customerNameList.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            CustomerManagement customerManagement = new CustomerManagement();
                          setDiscount(customerManagement.findByName(customerNameList.getSelectedItem().toString()));
                        }
                    });
                    repaint();
                    revalidate();
                    CloseCustomer();
            }
        });


    }
    public void renderProductEditDialog(){
        productEditGUI newGUI = new productEditGUI(null,orderDetail);
        newGUI.getOrderController().setObs(this);
        newGUI.setVisible(true);
        newGUI.setLocationRelativeTo(null);
    }
    public void searchProduct()
    {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> products = new ArrayList<>();
                products.clear();
                if(!txtSearch.getText().equals("")){
                    String pattern = ".*" + txtSearch.getText() + ".*";
                   for(Product product : loadData.products){
                     if(product.getProductName().toLowerCase().matches(pattern.toLowerCase())){
                         products.add(product);
                     }
                    }
                    if(products.size()>0){
                        render(products);
                        scrollPane.validate();
                    }else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm!!!!");
                        txtSearch.setText("");
                        productListPanel.removeAll();
                        render(loadData.products);
                        scrollPane.validate();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Nhập nội dung tìm kiếm!!!!");
                }
            }
        });
    }
    public void reloadTable(){
        ArrayList<OrderDetailsModel> arrayList = orderController.getOrderDetailsModels(orderDetails);
        Object [] columnName = {"STT","Sản phẩm","Size","Số lượng","Giá"};
        Object[][] data = new Object[arrayList.size()][5];
        for(int i=0;i<arrayList.size();i++){
            data[i][0]=i+1;
            data[i][1]=arrayList.get(i).getProduct();
            data[i][2]=arrayList.get(i).getSize();
            data[i][3]=arrayList.get(i).getQty();
            data[i][4]=arrayList.get(i).getTotalPrice();
        }
            cartTableModel.setDataVector(data,columnName);
        TableColumn column1 = cartTable.getColumnModel().getColumn(1);
        column1.setPreferredWidth(300);
        cartScrollPane.validate();
    }
    public void render(ArrayList<Product> products){
        //  productListPanel.invalidate();
        System.out.println("000"+products.size());
        if(productListPanel!=null){
            productListPanel.removeAll();
        }
        products.forEach(e->{
            Items item = new Items(e);
            item.getOrderController().setObs(this);
            productListPanel.add(item);
        });
    }
    public OrderDetail orderDetailSelected(Integer selectedRow){
        OrderDetail orderDetail = new OrderDetail();
        Object productName = getCartTable().getValueAt(selectedRow,1);
        Object size = getCartTable().getValueAt(selectedRow,2);
        for(OrderDetail o : orderDetails){
            if(o.getProduct().getProductName().equalsIgnoreCase(String.valueOf(productName)) && o.getSize().equalsIgnoreCase(String.valueOf(size))){
                orderDetail=o;
            }
        }
        return orderDetail;
    }
    public OrderDetail select(){
        getCartTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    Integer selected = getCartTable().getSelectedRow();
                    if(selected!=-1) {
                        orderDetail=orderDetailSelected(selected);
                    }

                }
            }
        });
        return orderDetail;
    }
    public void CloseCustomer(){

        btnCloseCustomer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(
                            "dô"
                    );
                    customerPanel.remove(customerNameList);
                    customerPanel.remove(btnCloseCustomer);
                    customerPanel.repaint();
                    repaint();
                    revalidate();
                    btnCustomer.setEnabled(true);
                }
            });
    }
    public void delete(OrderDetail orderDetail, Integer row){
        orderDetails.remove(orderDetail);
        getCartTableModel().removeRow(row);
        getCartTableModel().fireTableDataChanged();
        getCartScrollPane().validate();
        reloadTable();
    }
    public void setDiscount(Customer customer){
        CustomerManagement customerManagement = new CustomerManagement();
        if(customerManagement.PointOfCustomer(customer).size()<1){
            labelInfo = new JLabel("Bạn chưa đủ điều kiện");
            labelInfo.setFont(new Font("SF Pro Display", Font.BOLD, 18));
            customerPointPanel.add(labelInfo);
            repaint();
            revalidate();
        }else {
            String [] cbNPoint = customerManagement.PointOfCustomer(customer).toArray(new String[0]);
            customerPointList = new JComboBox<>(cbNPoint);
            customerPointPanel.add(customerPointList);
            repaint();
            revalidate();
            customerPointList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Do 2");
                    labelPricePoint = new JLabel(String.valueOf(Integer.parseInt(customerPointList.getSelectedItem().toString())*50000));
                    customerPointPanel.add(labelPrice);
                    btnClosePoint = new JButton("x");
                    customerPointPanel.add(btnClosePoint);
                    repaint();
                    revalidate();
                }
            });
        }
        repaint();
        revalidate();
    }

}