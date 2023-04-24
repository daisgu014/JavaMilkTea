package App.View;

import App.Controller.PrintPDF;
import App.View.Shop.Controller.OrderController;
import App.View.Shop.model.OrderDetailsModel;
import Entity.Order;
import Logic.OrderManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

import static App.View.Shop.loadData.orderDetails;

public class Receipt extends JPanel {
    private JPanel header,content, footer, leftPanel, rightPanel;
    private JTable orderDetailsTable;
    private DefaultTableModel defaultTableModel;
    private JScrollPane cartScrollPane;
    private JLabel totalPrice, casier, customer;
    private OrderController orderController;
    OrderManagement orderManagement = new OrderManagement();
    public Receipt(Order order){
        orderController = new OrderController();
        setSize(794,
                900);
        setLayout(new BorderLayout());

        /*Content*/
        /*Header Content Chữ Receipt*/
        content = new JPanel(new BorderLayout());
         JPanel headerContent = new JPanel(new GridLayout(0,1));
         JLabel header = new JLabel();
         header.setText("RECEIPT #"+order.getOrderId());
        header.setHorizontalAlignment(0);
         /*Hiện tên nhân viên và người mua*/
         JPanel peoplePanel = new JPanel(new GridLayout(2,2));
         JLabel Employee = new JLabel("Employee Name: ");
         Employee.setHorizontalAlignment(0);
         casier = new JLabel("");
         casier.setText(order.getCashier().getEmployeeName());
        JLabel customerName = new JLabel("Customer Name: ");
        customerName.setHorizontalAlignment(0);
        customer = new JLabel("Alias");
        if(order.getCustomer()!=null){
            customer.setText(order.getCustomer().getCustomerName());
        }
        peoplePanel.add(Employee);
        peoplePanel.add(casier);
        peoplePanel.add(customerName);
        peoplePanel.add(customer);
         headerContent.add(header);
         headerContent.add(peoplePanel);
         content.add(headerContent,BorderLayout.NORTH);
         /*Table*/
        ArrayList<OrderDetailsModel> orderDetailsModels = orderController.getOrderDetailsModels(orderManagement.orderDetails(order.getOrderId()));
        Object[][] data = new Object[orderDetailsModels.size()][5];
        for(int i=0;i<orderDetailsModels.size();i++){
            data[i][0]=i+1;
            data[i][1]=orderDetailsModels.get(i).getProduct();
            data[i][2]=orderDetailsModels.get(i).getSize();
            data[i][3]=orderDetailsModels.get(i).getQty();
            data[i][4]=orderDetailsModels.get(i).getTotalPrice();
        }

        Object [] columnName = {"STT","Sản phẩm","Size","Số lượng","Giá"};
        defaultTableModel = new DefaultTableModel(data,columnName);
        orderDetailsTable=new JTable(defaultTableModel);
        cartScrollPane = new JScrollPane(orderDetailsTable);
        TableColumn column1 = orderDetailsTable.getColumnModel().getColumn(1);
        column1.setPreferredWidth(300);
        content.add(cartScrollPane,BorderLayout.CENTER);
        /*Header*/
        JPanel info = new JPanel(new GridLayout(0,1));
        Label javaCoffee = new Label("JAVA COFFEE");
        Label address = new Label("Địa chỉ: 273 An Dương Vương, Phường 3, Q5, TP. Hồ Chí Minh");
        info.add(javaCoffee);
        info.add(address);
        JPanel emtyPane = new JPanel();
        JLabel date = new JLabel();
        date.setText(String.valueOf(order.getOrderDate()));
        date.setHorizontalAlignment(0);
        emtyPane.add(date);
        emtyPane.setPreferredSize(new Dimension(400,100));
        JPanel headerPaper = new JPanel();
        headerPaper.add(info);
        headerPaper.add(emtyPane);
        add(headerPaper,BorderLayout.NORTH);
        add(content,BorderLayout.CENTER);
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(50,50));
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(50,50));
        add(leftPanel,BorderLayout.WEST);
        add(rightPanel,BorderLayout.EAST);
        /*Footer*/
        footer = new JPanel(new GridLayout(1,0));
        JPanel jPanel = new JPanel();
        JPanel totalPanel = new JPanel();
        JLabel totalLabel = new JLabel("Tổng tiền: ");
        totalPrice = new JLabel();
        totalPrice.setText(String.valueOf(order.getTotalPrice()));
        totalPanel.add(totalLabel);
        totalPanel.add(totalPrice);
        footer.add(jPanel);
        footer.add(totalPanel);
        footer.setPreferredSize(new Dimension(600,50));
        add(footer,BorderLayout.SOUTH);
    }
}


