package App.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductDetails extends JPanel {
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;

    public ProductDetails() {
        // Thiết lập layout cho panel chi tiết sản phẩm
        setLayout(new GridBagLayout());

        // Thêm hình ảnh sản phẩm
        imageLabel = new JLabel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(imageLabel, gbc);

        // Thêm tên sản phẩm
        nameLabel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 0, 10);
        add(nameLabel, gbc);

        // Thêm giá tiền
        priceLabel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 0, 10);
        add(priceLabel, gbc);

        // Thêm mô tả sản phẩm
        descriptionLabel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(descriptionLabel, gbc);
    }

//    public void setProduct(Product product) {
//        // Thiết lập thông tin chi tiết sản phẩm
//        imageLabel.setIcon(new ImageIcon(product.getImagePath()));
//        nameLabel.setText(product.getName());
//        priceLabel.setText(String.format("%,d đồng", product.getPrice()));
//        descriptionLabel.setText("<html>" + product.getDescription() + "</html>");
//    }
}
