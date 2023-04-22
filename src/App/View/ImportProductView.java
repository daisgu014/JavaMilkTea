package App.View;

import App.Controller.ImportController;
import App.View.Shop.loadData;
import Entity.Import;
import Entity.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ImportProductView extends JPanel {
    private JTable productTable;
    private DefaultTableModel model;
    private JComboBox<String> products, sizes;
    private JTextField qtyTxf;
    private JButton addBtn, editBtn, deleteBtn;
    private JLabel qtyLbl, productLbl, title, sizeLbl;
    private JPanel leftContainer;
    private JScrollPane scrollPane;
    private ImportController controller;


    public ImportProductView() {
        initGUI();
    }

    public void initGUI() {
        this.setLayout(new BorderLayout());
        controller = new ImportController();

        title = new JLabel("Import Product", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 50));
        model = new DefaultTableModel();
        productTable = new JTable(model);

        model.addColumn("No.");
        model.addColumn("Product");
        model.addColumn("Size");
        model.addColumn("Import Quantity");
        scrollPane = new JScrollPane(productTable);

        initLeftGUI();
        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(leftContainer, BorderLayout.EAST);
        handleEvents();
    }

    public void initLeftGUI() {
        leftContainer = new JPanel();
        leftContainer.setPreferredSize(new Dimension(500, 700));

        productLbl = new JLabel("Product");
        qtyLbl = new JLabel("Quantity");
        sizeLbl = new JLabel("Size");
//        products = new JComboBox<>();
//        sizes = new JComboBox<>();
        qtyTxf = new JTextField();
        initComboBox();

        productLbl.setPreferredSize(new Dimension(0, 20));
        qtyLbl.setPreferredSize(new Dimension(0, 20));
        products.setPreferredSize(new Dimension(200, 30));
        qtyTxf.setPreferredSize(new Dimension(200, 30));

        JPanel panel1 = new JPanel(new GridLayout(3, 2, 0, 30));
        panel1.add(productLbl);
        panel1.add(products);
        panel1.add(sizeLbl);
        panel1.add(sizes);
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

    public void setDataTable(ArrayList<Object[]> objects) {
        model.setRowCount(0);
        for (Object[] o : objects) {
            model.addRow(o);
        }
    }

    public void initComboBox() {
        String[] strings = controller.getModel().getProductNames().toArray(new String[0]);
        products = new JComboBox<>(strings);
        products.setSelectedIndex(-1);
        sizes = new JComboBox<>();
    }

    public void handleEvents() {
        products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizes.removeAllItems();
                String name = (String) products.getSelectedItem();
                String[] strings = controller.getModel().getSizesByProductName(name).toArray(new String[0]);
                for (String string : strings) {
                    sizes.addItem(string);
                }
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = (String) products.getSelectedItem();
                    String size = (String) sizes.getSelectedItem();
                    int qty = Integer.parseInt(qtyTxf.getText());
                    if (name != null && size != null && qty > 0) {
                        controller.getModel().getLogic().addPreparations(name, size, qty);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception exception) {
//                    System.out.println("Looi them preparation");
                    System.out.println(exception);
                }
            }
        });
    }

}
