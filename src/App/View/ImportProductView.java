package App.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ImportProductView extends JPanel {
    private JTable productTable;
    private DefaultTableModel model;
    private JComboBox products;
    private JTextField qtyTxf;
    private JButton addBtn, editBtn, deleteBtn;
    private JLabel qtyLbl, productLbl, title;
    private JPanel mainContainer, leftContainer;

    public ImportProductView() {
        initGUI();
    }

    public void initGUI() {
        this.setLayout(new BorderLayout());

        title = new JLabel("Import Product", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 50));
        model = new DefaultTableModel();
        productTable = new JTable(model);

        model.addColumn("No.");
        model.addColumn("Product");
        model.addColumn("Import Quantity");
        JScrollPane scrollPane = new JScrollPane(productTable);

        initLeftGUI();
        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(leftContainer, BorderLayout.EAST);
    }

    public void initLeftGUI() {
        leftContainer = new JPanel();
        leftContainer.setPreferredSize(new Dimension(500, 700));

        productLbl = new JLabel("Product");
        qtyLbl = new JLabel("Quantity");
        products = new JComboBox<>();
        qtyTxf = new JTextField();

        productLbl.setPreferredSize(new Dimension(0, 20));
        qtyLbl.setPreferredSize(new Dimension(0, 20));
        products.setPreferredSize(new Dimension(200, 30));
        qtyTxf.setPreferredSize(new Dimension(200, 30));

        JPanel panel1 = new JPanel(new GridLayout(3, 3, 0, 30));
        panel1.add(productLbl);
        panel1.add(products);
        panel1.add(qtyLbl);
        panel1.add(qtyTxf);

        addBtn = new JButton("Add");
//        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");

        JPanel panel2 = new JPanel(new GridLayout(1, 2, 50, 0));
        panel2.add(addBtn);
//        panel2.add(editBtn);
        panel2.add(deleteBtn);

        leftContainer.add(panel1);
        leftContainer.add(panel2);
    }

}
