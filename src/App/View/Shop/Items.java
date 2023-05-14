package App.View.Shop;

import App.View.Shop.Controller.OrderController;
import Entity.OrderDetail;
import Entity.Product;
import Logic.ProductManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Items extends JPanel {
    private JLabel productName, productPrice, image, qtyLabel;
    private JComboBox<String> cbSize;
    private JButton purchase, btnSub, btnAdd;
    private ImageIcon imageIcon;
    private JPanel info;
    OrderController orderController;
    ProductManagement productManagement;


    public OrderController getOrderController() {
        return orderController;
    }

    public Items(Product product) {
        super(new BorderLayout());
        orderController = new OrderController();
        productManagement = new ProductManagement();
        setBorder(BorderFactory.createLineBorder(new Color(250, 152, 58), 3));
        setPreferredSize(new Dimension(100, 400));
        imageIcon = new ImageIcon(product.getImagePath());
        image = new JLabel(imageIcon);
        image.setPreferredSize(new Dimension(100, 200));
        add(image, BorderLayout.CENTER);
        info = new JPanel(new GridLayout(2, 2, 10, 10));
        info.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productName = new JLabel(product.getProductName());
        productName.setFont(new Font("SF Pro Display", Font.BOLD, 14));
        String[] productSize = product.getProductSizesString().toArray(new String[0]);
        cbSize = new JComboBox<>(productSize);
        cbSize.setSelectedItem(productSize[1]);
        productPrice = new JLabel(String.valueOf(product.getPrice((String) cbSize.getSelectedItem())));
        productPrice.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        cbSize.setPreferredSize(new Dimension(40, 40));
        purchase = new JButton("Order");
        purchase.setBackground(null);
        purchase.setBorder(BorderFactory.createLineBorder(new Color(246, 229, 141)));
        purchase.setFont(new Font("SF Pro Display", Font.BOLD, 18));
        JPanel panelPrice = new JPanel();
        JPanel QtyPanel = new JPanel();
        btnSub = new JButton("-");
        btnAdd = new JButton("+");
        qtyLabel = new JLabel("1");
        QtyPanel.add(btnSub);
        QtyPanel.add(qtyLabel);
        btnSub.setPreferredSize(new Dimension(40, 40));
        btnSub.setBackground(new Color(223, 0, 41));
        btnSub.setForeground(Color.WHITE);
        btnSub.setFont(new Font("SF Pro Display", Font.BOLD, 10));
        btnAdd.setPreferredSize(new Dimension(40, 40));
        btnAdd.setBackground(new Color(91, 189, 43));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("SF Pro Display", Font.BOLD, 10));
        qtyLabel.setFont(new Font("SF Pro Display", Font.BOLD, 18));
        qtyLabel.setPreferredSize(new Dimension(30, 30));
        qtyLabel.setHorizontalAlignment(0);
        QtyPanel.add(btnAdd);
        QtyPanel.setBackground(null);
        panelPrice.add(cbSize);
        panelPrice.add(productPrice);
        info.add(productName);
        panelPrice.setBackground(null);
        info.add(panelPrice);
        info.add(QtyPanel);
        info.add(purchase);
        info.setBackground(new Color(250, 152, 58));
        add(info, BorderLayout.SOUTH);
        cbSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productPrice.setText(String.valueOf(product.getPrice((String) cbSize.getSelectedItem())));
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
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderController.AddOrderDetails(null, addOrderDetails());
                orderController.getObs().reloadTable();
                orderController.getObs().TotalPrice();
            }
        });

    }

    public OrderDetail addOrderDetails() {
        OrderDetail orderDetail = new OrderDetail();
        Integer qty = Integer.parseInt(qtyLabel.getText());
        String product = productName.getText();
        String size = String.valueOf(cbSize.getSelectedItem());
        System.out.println(size);
        Product productSelected = productManagement.findByName(product);
        orderDetail.setProduct(productSelected);
        orderDetail.setQuantity(qty);
        orderDetail.setSize(size);
        System.out.println("size: " + orderDetail.getSize());
        return orderDetail;
    }


}
