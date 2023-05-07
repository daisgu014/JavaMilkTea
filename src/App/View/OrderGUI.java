package App.View;

import App.Controller.PrintPDF;
import App.Model.OrderModel;
import App.View.Shop.loadData;
import Entity.Customer;
import Entity.Order;
import Logic.CustomerManagement;
import Logic.EmployeeManagement;
import Logic.OrderManagement;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class OrderGUI extends JPanel {
    private JTable orderTable;
    private DefaultTableModel orderTableModel;
    private JButton btnReceipt, btnPrintf;
    private JPanel header, main, footer;
    private JScrollPane jScrollPane;
    private JLabel label;
    CustomerManagement customerManagement = new CustomerManagement();
    EmployeeManagement employeeManagement = new EmployeeManagement();
    Integer selectedRow = null;

    public OrderGUI() {
        /*Header*/
        setLayout(new BorderLayout());
        label = new JLabel();
        label.setHorizontalAlignment(0);
        label.setText("Order Management");
        label.setFont(new Font("SF Pro Display", Font.BOLD, 25));
        header = new JPanel();
        header.add(label, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
        /*Main*/
        /*Table Order*/
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        for (Order order : loadData.orders) {
            orderModels.add(new OrderModel(order.getOrderId(), order.getCustomer(), order.getOrderDate(), order.getTotalPrice(), order.getCashier()));

        }
        Object[] columnName = {"OrderID", "Khách hàng", "Ngày mua", "Giá tiền", "Nhân viên bán"};
        Object[][] data = new Object[orderModels.size()][5];
        for (int i = 0; i < orderModels.size(); i++) {
            data[i][0] = orderModels.get(i).getOrderId();
            data[i][1] = orderModels.get(i).getCustomer().getCustomerName();
            data[i][2] = orderModels.get(i).getDate();
            data[i][3] = orderModels.get(i).getTotalPrice();
            data[i][4] = orderModels.get(i).getEmployee().getEmployeeName();
        }
        orderTableModel = new DefaultTableModel(data, columnName);
        orderTable = new JTable(orderTableModel);
        jScrollPane = new JScrollPane(orderTable);
        jScrollPane.setPreferredSize(new Dimension(900, 500));
        main = new JPanel();
        main.add(jScrollPane, BorderLayout.CENTER);
        add(main, BorderLayout.CENTER);
        /*footer*/
        btnPrintf = new JButton("Print");
        footer = new JPanel();
        footer.setPreferredSize(new Dimension(100, 100));
        footer.add(btnPrintf);
        add(footer, BorderLayout.SOUTH);
        main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                reloadTable();
            }
        });
        orderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow = orderTable.getSelectedRow();
                }
            }
        });
        btnPrintf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow == -1 || selectedRow == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xem");
                } else {
                    DialogReceipt dialogReceipt = new DialogReceipt(order(selectedRow));
                    dialogReceipt.setVisible(true);
                    dialogReceipt.setLocationRelativeTo(null);
                }
            }
        });
    }


    public Order order(Integer selectedRow) {
        OrderManagement orderManagement = new OrderManagement();
        Object id = orderTable.getValueAt(selectedRow, 0);
        return orderManagement.findById(Integer.parseInt(String.valueOf(id)));

    }

    public void reloadTable() {
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        for (Order order : loadData.orders) {
            orderModels.add(new OrderModel(order.getOrderId(), order.getCustomer(), order.getOrderDate(), order.getTotalPrice(), order.getCashier()));

        }
        Object[] columnName = {"OrderID", "Khách hàng", "Ngày mua", "Giá tiền", "Nhân viên bán"};
        Object[][] data = new Object[orderModels.size()][5];
        for (int i = 0; i < orderModels.size(); i++) {
            data[i][0] = orderModels.get(i).getOrderId();
            if (orderModels.get(i).getCustomer() != null) {
                data[i][1] = orderModels.get(i).getCustomer().getCustomerName();
            }
            data[i][2] = orderModels.get(i).getDate();
            data[i][3] = orderModels.get(i).getTotalPrice();
            data[i][4] = orderModels.get(i).getEmployee().getEmployeeName();
        }
        orderTableModel.setDataVector(data, columnName);
        jScrollPane.repaint();
        jScrollPane.revalidate();
    }
}
