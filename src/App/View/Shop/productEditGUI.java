package App.View.Shop;

import App.View.Shop.Controller.OrderController;
import Entity.OrderDetail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class productEditGUI extends JDialog  {
    private ImageIcon imageIcon;
    private JLabel productName, productPrice, image, qtyLabel;
    private JButton btnSub, btnAdd,btnClose, btnEdit;
    private JComboBox<String> cbSize;
    JPanel panel,panelProduct, info;

    public OrderController getOrderController() {
        return orderController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    private OrderController orderController;

    public productEditGUI(JFrame parent, OrderDetail orderDetail){
        orderController  = new OrderController();
        setSize(500,500);
        panelProduct=new JPanel(new BorderLayout());
        imageIcon = new ImageIcon(orderDetail.getProduct().getImagePath());
        System.out.println(orderDetail.getProduct().getImagePath());
        image= new JLabel(imageIcon);
        image.setPreferredSize(new Dimension(100,200));
        add(image,BorderLayout.CENTER);
        info = new JPanel(new GridLayout(2,2, 10, 10));
        info.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productName=new JLabel(orderDetail.getProduct().getProductName());
        productName.setFont(new Font("Arial",Font.BOLD, 14));
        String [] productSize = orderDetail.getProduct().getProductSizesString().toArray(new String[0]);
        cbSize = new JComboBox<>(productSize);
        cbSize.setSelectedItem(orderDetail.getSize());
        productPrice = new JLabel(String.valueOf(orderDetail.getProduct().getPrice((String) cbSize.getSelectedItem())));
        productPrice.setFont(new Font("Arial", Font.BOLD, 20));
        cbSize.setPreferredSize(new Dimension(40, 40));
        JPanel panelPrice = new JPanel();
        JPanel QtyPanel = new JPanel();
        btnSub = new JButton("-");
        btnAdd = new JButton("+");
        qtyLabel = new JLabel(String.valueOf(orderDetail.getQuantity()));
        QtyPanel.add(btnSub);
        QtyPanel.add(qtyLabel);
        btnSub.setPreferredSize(new Dimension(45,45));
        btnSub.setBackground(new Color(223,0,41));
        btnSub.setForeground(Color.WHITE);
        btnSub.setFont(new Font("Arial", Font.BOLD, 18));
        btnAdd.setPreferredSize(new Dimension(45,45));
        btnAdd.setBackground(new Color(91,189,43));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 18));
        qtyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        qtyLabel.setPreferredSize(new Dimension(30,30));
        qtyLabel.setHorizontalAlignment(0);
        QtyPanel.add(btnAdd);
        QtyPanel.setBackground(null);
        panelPrice.add(cbSize);
        panelPrice.add(productPrice);
        info.add(productName);
        panelPrice.setBackground(null);
        info.add(panelPrice);
        info.add(QtyPanel);
        info.setBackground(new Color(250, 152, 58));
        panelProduct.add(info,BorderLayout.SOUTH);
        panel = new JPanel(new BorderLayout());
        panel.add(panelProduct,BorderLayout.CENTER);
        btnClose= new JButton("CLOSE");
        btnEdit = new JButton("EDIT");
        JPanel footer =new JPanel();
        footer.add(btnEdit);
        footer.add(btnClose);
        panel.setSize(400,400);
        panel.add(footer, BorderLayout.SOUTH);
       add(panel, BorderLayout.SOUTH);
        cbSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productPrice.setText(String.valueOf(orderDetail.getProduct().getPrice((String) cbSize.getSelectedItem())));
            }
        });
        btnSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int inputValue = Integer.parseInt(qtyLabel.getText());
                if (inputValue > 1) {
                    inputValue -= 1;
                    qtyLabel.setText(String.valueOf(inputValue));
                }
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (qtyLabel.getText().equalsIgnoreCase("")) {
                    qtyLabel.setText("1");
                }
                int inputValue = Integer.parseInt(qtyLabel.getText());
                inputValue += 1;
                qtyLabel.setText(String.valueOf(inputValue));
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderController.EditEvent(orderDetail,String.valueOf(cbSize.getSelectedItem()),Integer.parseInt(qtyLabel.getText()));
                orderController.getObs().reloadTable();
                orderController.getObs().selectRow=-1;
                orderController.getObs().TotalPrice();
                dispose();
            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderController.getObs().reloadTable();
                orderController.getObs().selectRow=-1;
                dispose();
            }
        });
    }

}
