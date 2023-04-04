package App.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TraSuaGUI extends JFrame {
    private JComboBox<String> comboBox;
    private JButton btn1, btn2, btn3;
    private JTable cartTable;
    private DefaultTableModel cartTableModel;

    public TraSuaGUI() {
        // Tạo panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tạo panel danh sách sản phẩm
        JPanel productListPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        productListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Thêm sản phẩm vào panel danh sách sản phẩm
        for (int i = 1; i <= 15; i++) {
            String productName = "Sản phẩm " + i;
            int productPrice = i * 10000;
            ImageIcon productImage = new ImageIcon("product_" + i + ".jpg");

            // Tạo panel sản phẩm
            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setPreferredSize(new Dimension(180, 220));

            // Hiển thị hình ảnh sản phẩm
            JLabel imageLabel = new JLabel(productImage);
            imageLabel.setPreferredSize(new Dimension(150, 150));
            productPanel.add(imageLabel, BorderLayout.CENTER);

            // Hiển thị thông tin sản phẩm
            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            JLabel nameLabel = new JLabel(productName);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            infoPanel.add(nameLabel);
            JLabel priceLabel = new JLabel("Giá: " + productPrice + " đ");
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            infoPanel.add(priceLabel);
            productPanel.add(infoPanel, BorderLayout.SOUTH);

            // Thêm panel sản phẩm vào panel danh sách sản phẩm
            productListPanel.add(productPanel);
        }

        // Thêm thanh cuộn cho panel danh sách sản phẩm
        JScrollPane scrollPane = new JScrollPane(productListPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Thêm panel danh sách sản phẩm vào panel chính
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Tạo panel bảng giỏ hàng
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartTableModel = new DefaultTableModel();
        cartTableModel.addColumn("Tên sản phẩm");
        cartTableModel.addColumn("Giá");
        cartTableModel.addColumn("Số lượng");
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        btn1 = new JButton("Thêm sản phẩm");
        btn2 = new JButton("Xoá sản phẩm");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lấy thông tin sản phẩm được chọn
                int selectedIndex = comboBox.getSelectedIndex();
                String productName = "Sản phẩm " + (selectedIndex + 1);
                int productPrice = (selectedIndex + 1) * 10000;

                // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng chưa
                int rowCount = cartTableModel.getRowCount();
                int existingIndex = -1;
                for (int i = 0; i < rowCount; i++) {
                    String name = (String) cartTableModel.getValueAt(i, 0);
                    if (name.equals(productName)) {
                        existingIndex = i;
                        break;
                    }
                }

                // Nếu sản phẩm đã tồn tại trong giỏ hàng thì tăng số lượng lên 1
                // Nếu chưa tồn tại thì thêm sản phẩm mới vào giỏ hàng với số lượng là 1
                if (existingIndex >= 0) {
                    int quantity = (int) cartTableModel.getValueAt(existingIndex, 2);
                    cartTableModel.setValueAt(quantity + 1, existingIndex, 2);
                } else {
                    Object[] rowData = {productName, productPrice, 1};
                    cartTableModel.addRow(rowData);
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xoá sản phẩm được chọn khỏi giỏ hàng
                int selectedIndex = cartTable.getSelectedRow();
                if (selectedIndex >= 0) {
                    cartTableModel.removeRow(selectedIndex);
                }
            }
        });
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);

        // Thêm panel nút thêm và xoá sản phẩm vào panel bảng giỏ hàng
        cartPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm panel bảng giỏ hàng vào panel chính
        mainPanel.add(cartPanel, BorderLayout.EAST);

        // Tạo combobox để chọn sản phẩm để thêm vào giỏ hàng
        String[] productNames = {"Sản phẩm 1", "Sản phẩm 2", "Sản phẩm 3", "Sản phẩm 4", "Sản phẩm 5", "Sản phẩm 6", "Sản phẩm 7", "Sản phẩm 8", "Sản phẩm 9", "Sản phẩm 10", "Sản phẩm 11", "Sản phẩm 12", "Sản phẩm 13", "Sản phẩm 14", "Sản phẩm 15"};
        comboBox = new JComboBox<String>(productNames);
        comboBox.setPreferredSize(new Dimension(150, 30));
        cartPanel.add(comboBox, BorderLayout.NORTH);
        ProductDetails detailsPanel = new ProductDetails();
        detailsPanel.setPreferredSize(new Dimension(300, 0));
        mainPanel.add(detailsPanel, BorderLayout.WEST);
        // Tạo và hiển thị frame
        setTitle("Cửa hàng trà sữa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TraSuaGUI();
    }
}

