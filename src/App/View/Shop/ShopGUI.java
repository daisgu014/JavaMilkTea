package App.View.Shop;

import App.Controller.CustomerController;
import App.View.CustomerGUI;
import App.View.DialogReceipt;
import App.View.Shop.Controller.OrderController;
import App.View.Shop.model.OrderDetailsModel;
import Entity.*;
import Logic.CustomerManagement;
import Logic.Management;
import Logic.OrderManagement;
import Logic.ProductManagement;
import Main.Main;

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

import static App.View.Shop.loadData.*;

public class ShopGUI extends JPanel {
    static OrderController orderController = new OrderController();
    private JTextField txtSearch;
    private JButton btnSearch, btnEdit, btnDelete, btnCustomer, btnCloseCustomer, btnClosePoint;
    private JPanel searchBox;
    private JPanel searchPanel;
    private JTable cartTable;
    private JLabel labelPricePoint, labelPrice, labelInfo;
    CustomerManagement customerManagement = new CustomerManagement();
    OrderManagement orderManagement = new OrderManagement();

    public JTable getCartTable() {
        return cartTable;
    }

    public DefaultTableModel getCartTableModel() {
        return cartTableModel;
    }

    public JScrollPane getCartScrollPane() {
        return cartScrollPane;
    }

    private JPanel productListPanel, customerGirdPanel, customerPanel, customerPointPanel, TotalPricePanel;
    private DefaultTableModel cartTableModel;
    private JScrollPane scrollPane;
    private JScrollPane cartScrollPane;
    private JButton btnOrder, btnClear;
    private JComboBox<String> customerNameList, customerPointList;
    OrderDetail orderDetail = null;
    Integer selectRow = null;
    Management management = new Management();

    public ShopGUI() {
        setLayout(new BorderLayout());
        /*JPanel hiển thị sản phẩm theo dạng grip : 3 cột */
        productListPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        productListPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        /*Gọi hàn hiển thị danh sách sản phẩm*/
        render(loadData.products);
        /*Khởi tạo đối tượng scrollPanel và thêm đối tượng productListPanel*/
        scrollPane = new JScrollPane(productListPanel);
        /*Cài đặt kích thước mặc định cho scroolPane*/
        scrollPane.setPreferredSize(new Dimension(1080, 800));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane, BorderLayout.CENTER);
        JPanel categoryPanel = new JPanel(new GridLayout(0, 1));
        categoryPanel.setPreferredSize(new Dimension(150, 800));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JButton btnAll = new JButton("All");
        categoryPanel.add(btnAll);
        /**
         * Xử lý sự kiện hiển thị danh sách sản phẩm theo  All
         */
        btnAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render(loadData.products);
                scrollPane.validate();
            }
        });
        /*Hiển thị danh sách các thể loại category và hiển thị danh sách sản phẩm theo ds thể loại*/
        loadData.categories.forEach(cate ->
        {
            ArrayList<Product> products = new ArrayList<>();
            String category = cate.getCategoryName();
            JButton jButton = new JButton(category);
            categoryPanel.add(jButton);
            /**
             * Xử lý sự kiện hiển thị danh sách sản phẩm theo thể loại
             */
            jButton.addActionListener(event -> {
                products.clear();
                for (Product o : loadData.products) {
                    if (management.getCategoryManagement().findById(o.getCategory()).getCategoryName().equalsIgnoreCase(category)) {
                        products.add(o);
                    }
                }
                System.out.println(products.size());
                render(products);
                scrollPane.validate();
            });
        });
        /*--------------------------------BẢNG GIỎ HÀNG----------------------------------------------*/


        JPanel orderTable = new JPanel(new BorderLayout());
        ArrayList<OrderDetailsModel> orderDetailsModels = orderController.getOrderDetailsModels(orderDetails);
        Object[][] data = new Object[orderDetailsModels.size()][5];
        for (int i = 0; i < orderDetailsModels.size(); i++) {
            data[i][0] = i + 1;
            data[i][1] = orderDetailsModels.get(i).getProduct();
            data[i][2] = orderDetailsModels.get(i).getSize();
            data[i][3] = orderDetailsModels.get(i).getQty();
            data[i][4] = orderDetailsModels.get(i).getTotalPrice();
        }

        Object[] columnName = {"STT", "Sản phẩm", "Size", "Số lượng", "Giá"};
        cartTableModel = new DefaultTableModel(data, columnName);
        cartTable = new JTable(cartTableModel);
        cartScrollPane = new JScrollPane(cartTable);
        TableColumn column1 = cartTable.getColumnModel().getColumn(1);
        column1.setPreferredWidth(300);
        cartTable.getTableHeader().setBackground(Color.ORANGE);
        cartTable.getTableHeader().setFont(new Font("SF Pro Display", Font.BOLD, 12));
        JPanel btnPanel = new JPanel();
        /*Làm màu nút Edit nè*/
        btnEdit = new JButton("Edit");
        btnEdit.setPreferredSize(new Dimension(120,40));
        btnEdit.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setBackground(new Color(251, 197, 49));
        /*Làm màu nút Delete*/
        btnDelete = new JButton("Delete");
        btnDelete.setPreferredSize(new Dimension(120,40));
        btnDelete.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(214, 48, 49));
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.setPreferredSize(new Dimension(200, 70));
        JPanel RightPanel = new JPanel(new GridLayout(0, 1));
        orderTable.setBackground(Color.ORANGE);
        orderTable.add(cartScrollPane, BorderLayout.CENTER);
        orderTable.add(btnPanel, BorderLayout.SOUTH);
        customerPanel = new JPanel();
        btnCustomer = new JButton("Customer");
        btnCustomer.setPreferredSize(new Dimension(150, 40));
        btnCustomer.setBackground(new Color(0, 151, 230));
        btnCustomer.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        btnCustomer.setForeground(Color.white);
        customerPanel.add(btnCustomer);
        JPanel orderBtnPanel = new JPanel();
        /*--------------Làm màu nút Order--------------------*/
        btnOrder = new JButton("ORDER");
        btnOrder.setPreferredSize(new Dimension(120,40));
        btnOrder.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setBackground(new Color(0, 148, 50));
        /*--------------Làm màu nút Clear--------------------*/
        btnClear = new JButton("CLEAR");
        btnClear.setPreferredSize(new Dimension(120,40));
        btnClear.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        btnClear.setForeground(Color.WHITE);
        btnClear.setBackground(new Color(234, 32, 39));
        orderBtnPanel.add(btnOrder);
        orderBtnPanel.add(btnClear);
        add(categoryPanel, BorderLayout.WEST);
        customerGirdPanel = new JPanel(new GridLayout(0, 1));
        customerGirdPanel.add(customerPanel);
        customerPointPanel = new JPanel();
        customerGirdPanel.add(customerPointPanel);
        TotalPricePanel = new JPanel();
        JLabel labelPriceTitle = new JLabel("Tổng tiền:   ");
        labelPriceTitle.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        labelPriceTitle.setHorizontalAlignment(0);
        labelPriceTitle.setForeground(new Color(25, 42, 86));
        labelPrice = new JLabel("0");
        labelPrice.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        labelPrice.setHorizontalAlignment(0);
        labelPrice.setForeground(new Color(232, 65, 24));
        TotalPricePanel.add(labelPriceTitle);
        TotalPricePanel.add(labelPrice);
        RightPanel.add(orderTable);
        RightPanel.add(customerGirdPanel);
        RightPanel.add(TotalPricePanel);
        RightPanel.add(orderBtnPanel);
        add(RightPanel, BorderLayout.EAST);

        /*------------------------THANH TÌM KIẾM SẢN PHẨM----------------------------------------------*/
        searchPanel = new JPanel();
        searchBox = new JPanel();
        btnSearch = new JButton("Search");
        txtSearch = new JTextField(40);
        txtSearch.setPreferredSize(new Dimension(400, 50));
        btnSearch.setPreferredSize(new Dimension(100, 50));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("SF Pro Display", Font.BOLD, 18));
        searchBox.setFont(new Font("SF Pro Display", Font.PLAIN, 18));
        btnSearch.setBackground(new Color(250, 152, 58));
        searchBox.add(txtSearch, BorderLayout.CENTER);
        searchBox.add(btnSearch, BorderLayout.CENTER);
        searchPanel.add(searchBox, BorderLayout.CENTER);
        searchBox.setPreferredSize(new Dimension(600, 60));
        searchBox.setBackground(new Color(250, 211, 144));
        searchPanel.setPreferredSize(new Dimension(700, 70));
        add(searchPanel, BorderLayout.NORTH);
        searchProduct();
        /*---------------------------------- LẤY DÒNG CỦA BẢNG ĐƯỢC CLICK----------------------------*/
        cartTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (cartTable.getSelectedRow() != -1) {
                        selectRow = cartTable.getSelectedRow();
                        orderDetail = orderDetailSelected(selectRow);
                    }

                }
            }
        });
        /*---------------------------XÓA ĐỐI TƯỢNG CỦA BẢNG---------------------------------------------*/
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderDetail == null || selectRow == -1 || selectRow == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa");
                } else {
                    int option = JOptionPane.showConfirmDialog(null, "Bạn chắc muốn xóa " + orderDetail.getProduct().getProductName() + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        delete(orderDetail, selectRow);
                        TotalPrice();
                    } else {
                        return;
                    }

                }
            }
        });

        /*-------------------------XÓA TẤT CẢ ĐỐI TƯỢNG CỦA BẢNG-----------------------------------------------*/
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderDetails.clear();
                labelPrice.setText("0");
                reloadTable();
            }
        });
        /*------------------------CHỈNH SỬA ĐỐI TƯỢNG TRÊN BẢNG--------------------------------------------------*/
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderDetail == null || selectRow == -1 || selectRow == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần chỉnh sửa");
                } else {
                    renderProductEditDialog();
                }
            }
        });
        /*----------------------HIỂN THỊ DANH SÁCH KHÁCH HÀNG----------------------------------------------------*/
        btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> customerNameListString = new ArrayList<>();
                for (Customer s : customers) {
                    customerNameListString.add(s.getCustomerName());
                }
                ;
                String[] cbName = customerNameListString.toArray(new String[0]);
                customerNameList = new JComboBox<>(cbName);
                customerNameList.setSelectedItem(cbName[0]);
                customerNameList.setFont(new Font("SF Pro Display", Font.BOLD, 15));
                customerNameList.setForeground(new Color(34, 47, 62));
                btnCloseCustomer = new JButton("x");
                btnCloseCustomer.setForeground(Color.WHITE);
                btnCloseCustomer.setBackground(new Color(232, 65, 24));
                customerPanel.add(customerNameList);
                customerPanel.add(btnCloseCustomer);
                initPoint(customerManagement.findByName(customerNameList.getSelectedItem().toString()));
                TotalPrice();
                closeCustomerPointEvent();
                customerPanel.repaint();
                repaint();
                revalidate();
                btnCustomer.setEnabled(false);
                customerNameList.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Customer customer = customerManagement.findByName(customerNameList.getSelectedItem().toString());
                        initPoint(customer);
                        TotalPrice();
                    }
                });
                CloseCustomer();
            }
        });
        /*-------------------------------SỰ KIỆN THANH TOÁN------------------------------------------------*/
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderDetails.size() > 0) {
                    int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thanh toán", "Thanh toán ơn hàng", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        OrderCartTable();
                    } else {
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm trước khi thanh toán");
                }
            }
        });
    }

    /*--------------------------------HIỂN THỊ DIALOG CHỈNH SỬA SẢN PHẨM---------------------------------------------------*/
    public void renderProductEditDialog() {
        productEditGUI newGUI = new productEditGUI(null, orderDetail);
        newGUI.getOrderController().setObs(this);
        newGUI.setVisible(true);
        newGUI.setLocationRelativeTo(null);
    }

    /*---------------------------------------HÀM TÌM KIẾM SẢN PHẨM------------------------------------------------------*/
    public void searchProduct() {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> products = new ArrayList<>();
                products.clear();
                if (!txtSearch.getText().equals("")) {
                    String pattern = ".*" + txtSearch.getText() + ".*";
                    for (Product product : loadData.products) {
                        if (product.getProductName().toLowerCase().matches(pattern.toLowerCase())) {
                            products.add(product);
                        }
                    }
                    if (products.size() > 0) {
                        render(products);
                        scrollPane.validate();
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm!!!!");
                        txtSearch.setText("");
                        productListPanel.removeAll();
                        render(loadData.products);
                        scrollPane.validate();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nhập nội dung tìm kiếm!!!!");
                }
            }
        });
    }
    /*-------------------------------------------HÀM LÀM MỚI LẠI BẢNG-----------------------------------------------*/

    public void reloadTable() {
        ArrayList<OrderDetailsModel> arrayList = orderController.getOrderDetailsModels(orderDetails);
        Object[] columnName = {"STT", "Sản phẩm", "Size", "Số lượng", "Giá"};
        Object[][] data = new Object[arrayList.size()][5];
        for (int i = 0; i < arrayList.size(); i++) {
            data[i][0] = i + 1;
            data[i][1] = arrayList.get(i).getProduct();
            data[i][2] = arrayList.get(i).getSize();
            data[i][3] = arrayList.get(i).getQty();
            data[i][4] = arrayList.get(i).getTotalPrice();
        }
        cartTableModel.setDataVector(data, columnName);
        TableColumn column1 = cartTable.getColumnModel().getColumn(1);
        cartTable.getTableHeader().setBackground(Color.ORANGE);
        cartTable.getTableHeader().setFont(new Font("SF Pro Display", Font.BOLD, 12));
        column1.setPreferredWidth(300);
        cartScrollPane.validate();
    }

    /*------------------------------------------HÀM HIỂN THỊ DANH SÁCH SẢN PHẨM---------------------------------*/
    public void render(ArrayList<Product> products) {
        //  productListPanel.invalidate();
        System.out.println("000" + products.size());
        if (productListPanel != null) {
            productListPanel.removeAll();
        }
        products.forEach(e -> {
            Items item = new Items(e);
            item.getOrderController().setObs(this);
            productListPanel.add(item);
        });
    }

    /*----------------------------------------LẤY ĐỐI TƯỢNG ORDERDETAILS TỪ BẢNG--------------------------------------------*/
    public OrderDetail orderDetailSelected(Integer selectedRow) {
        OrderDetail orderDetail = new OrderDetail();
        Object productName = getCartTable().getValueAt(selectedRow, 1);
        Object size = getCartTable().getValueAt(selectedRow, 2);
        for (OrderDetail o : orderDetails) {
            if (o.getProduct().getProductName().equalsIgnoreCase(String.valueOf(productName)) && o.getSize().equalsIgnoreCase(String.valueOf(size))) {
                orderDetail = o;
            }
        }
        return orderDetail;
    }
//
//    public OrderDetail select() {
//        getCartTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    Integer selected = getCartTable().getSelectedRow();
//                    if (selected != -1) {
//                        orderDetail = orderDetailSelected(selected);
//                    }
//
//                }
//            }
//        });
//        return orderDetail;
//    }

    public void CloseCustomer() {

        btnCloseCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerNameList = new JComboBox<>();
                customerPanel.removeAll();
                customerPanel.add(btnCustomer);
                btnCustomer.setPreferredSize(new Dimension(150, 40));
                btnCustomer.setBackground(new Color(0, 151, 230));
                btnCustomer.setFont(new Font("SF Pro Display", Font.BOLD, 20));
                btnCustomer.setForeground(Color.white);
                customerPanel.repaint();
                closeCustomerPoint();
                Integer price = 0;
                for (OrderDetail o : orderDetails) {
                    price += o.getProduct().getPrice(o.getSize()) * o.getQuantity();
                }
                labelPrice.setText(String.valueOf(price));
                repaint();
                revalidate();
                btnCustomer.setEnabled(true);
            }

        });
    }

    public void delete(OrderDetail orderDetail, Integer row) {
        orderDetails.remove(orderDetail);
        getCartTableModel().removeRow(row);
        getCartTableModel().fireTableDataChanged();
        getCartScrollPane().validate();
        reloadTable();
    }

//    public void setDiscount(Customer customer) {
//        if (customerManagement.PointOfCustomer(customer).size() < 1) {
//            labelInfo = new JLabel("Bạn chưa đủ điều kiện");
//            labelInfo.setFont(new Font("SF Pro Display", Font.BOLD, 18));
//            customerPointPanel.add(labelInfo);
//            repaint();
//            revalidate();
//        } else {
//            String[] cbNPoint = customerManagement.PointOfCustomer(customer).toArray(new String[0]);
//            customerPointList = new JComboBox<>(cbNPoint);
//            customerPointPanel.add(customerPointList);
//            repaint();
//            revalidate();
//            customerPointList.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    repaint();
//                    revalidate();
//                    labelPricePoint.setText(String.valueOf(Integer.parseInt(customerPointList.getSelectedItem().toString()) * 5000));
//                    TotalPrice();
//                }
//            });
//        }
//    }

    public void initPoint(Customer customer) {
        customerPointPanel.removeAll();
        if (customerManagement.PointOfCustomer(customer).size() < 1) {
            labelInfo = new JLabel("Bạn chưa đủ điều kiện");
            labelInfo.setFont(new Font("SF Pro Display", Font.BOLD, 18));
            labelInfo.setHorizontalAlignment(0);
            labelInfo.setForeground(new Color(232, 65, 24));
            customerPointPanel.add(labelInfo);
        } else {
            String[] cbNPoint = customerManagement.PointOfCustomer(customer).toArray(new String[0]);
            customerPointList = new JComboBox<>(cbNPoint);
            customerPointPanel.add(customerPointList);
            labelPricePoint = new JLabel(String.valueOf(Integer.parseInt(customerPointList.getSelectedItem().toString()) * 5000));
            labelPricePoint.setFont(new Font("SF Pro Display", Font.BOLD, 15));
            labelPricePoint.setForeground(new Color(34, 47, 62));
            customerPointPanel.add(labelPricePoint);
            btnClosePoint = new JButton("x");
            btnClosePoint.setForeground(Color.WHITE);
            btnClosePoint.setBackground(new Color(232, 65, 24));
            customerPointPanel.add(btnClosePoint);
            customerPointList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Do 2");
                    labelPricePoint.setText(String.valueOf(Integer.parseInt(customerPointList.getSelectedItem().toString()) * 5000));
                    repaint();
                    revalidate();
                    TotalPrice();
                }
            });
        }
        TotalPrice();
        repaint();
        revalidate();
        closeCustomerPointEvent();
    }

    public void closeCustomerPoint() {
        if (labelPricePoint != null) {
            labelPricePoint.setText("");
            customerPointList = new JComboBox<>();
            customerPointPanel.removeAll();
        }

        Integer price = 0;
        for (OrderDetail o : orderDetails) {
            price += o.getProduct().getPrice(o.getSize()) * o.getQuantity();
        }
        labelPrice.setText(String.valueOf(price));
        repaint();
        revalidate();
    }

    public void closeCustomerPointEvent() {
        btnClosePoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCustomerPoint();
            }
        });
    }

    public void TotalPrice() {
        Integer price = 0;
        for (OrderDetail o : orderDetails) {
            price += o.getProduct().getPrice(o.getSize()) * o.getQuantity();
        }
        labelPrice.setText(String.valueOf(price));
        if (customerNameList != null
                && customerNameList.getSelectedItem() != null
                && customerManagement.findByName(customerNameList.getSelectedItem().toString()).getPoints() >= 10
                && customerPointList != null) {
            if (!labelPricePoint.getText().equalsIgnoreCase("")) {
                if (price - Integer.parseInt(labelPricePoint.getText()) > 0) {
                    labelPrice.setText(String.valueOf(price - Integer.parseInt(labelPricePoint.getText())));
                } else {
                    labelPrice.setText("0");
                }
            }
        }
        labelPrice.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        labelPrice.setHorizontalAlignment(0);
        labelPrice.setForeground(new Color(232, 65, 24));
        TotalPricePanel.repaint();
        repaint();
        revalidate();
    }

    public Order order(ArrayList<OrderDetail> orderDetails) {
        return orderManagement.createWithNoPhone(orderDetails);
    }

    public Order order(ArrayList<OrderDetail> orderDetails, Customer customer, Integer vlue) {
        return orderManagement.createWithPhone(orderDetails, customer, vlue);
    }

    public void displayReceipt(Order order) {
        DialogReceipt dialogReceipt = new DialogReceipt(order);
        dialogReceipt.setLocationRelativeTo(null);
        dialogReceipt.setVisible(true);
    }

    public void OrderCartTable() {
        Order order;
        if (customerNameList == null || customerNameList.getSelectedItem() == null) {
            order = order(orderDetails);
            orders.add(order);
            displayReceipt(order);
        } else {
            order = order(orderDetails,
                    customerManagement.findByName(customerNameList.getSelectedItem().toString()),
                    Integer.parseInt(labelPrice.getText()));
            if (customerManagement.findByName(customerNameList.getSelectedItem().toString()).getPoints() >= 10 && labelPricePoint != null && customerPointList.getSelectedItem() != null) {
                customerManagement.Update_Sub_Point(customerManagement.findByName(customerNameList.getSelectedItem().toString()), Integer.parseInt(customerPointList.getSelectedItem().toString()));
                for (Customer customer : customers) {
                    if (customer.getCustomerName().equalsIgnoreCase(customerNameList.getSelectedItem().toString())) {
                        customer.setPoints(customer.getPoints() - Integer.parseInt(customerPointList.getSelectedItem().toString()));
                    }
                }
                CustomerGUI customerGUI = new CustomerGUI();
                customerGUI.reloadTable();
            }
            orders.add(order);
            displayReceipt(order);
            customerPanel.remove(customerNameList);
            customerPanel.remove(btnCloseCustomer);
            customerPanel.repaint();
            btnCustomer.setEnabled(true);
        }
        orderDetails.clear();
        reloadTable();
        closeCustomerPoint();
        repaint();
        revalidate();
    }
}