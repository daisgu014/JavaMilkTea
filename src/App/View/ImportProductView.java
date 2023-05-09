package App.View;

import App.Controller.ImportController;
import Entity.Import;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ImportProductView extends JPanel {
    private JTable productTable;
    private DefaultTableModel model;
    private JComboBox<String> products, sizes;
    private JTextField qtyTxf;
    private JButton addBtn, editBtn, deleteBtn, importBtn, refreshBtn;
    private JLabel qtyLbl, productLbl, title, sizeLbl;
    private JPanel leftContainer;
    private JScrollPane scrollPane;
    private ImportController controller;
    private static Color c = new Color(247, 159, 31);

    public ImportProductView() {
        initGUI();
    }

    public void initGUI() {
        this.setLayout(new BorderLayout(0, 16));
        controller = new ImportController();

        title = new JLabel("Import Product", SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,30));
        title.setBorder(BorderFactory.createLineBorder(c));
        title.setForeground(c);
        title.setBackground(Color.white);
        title.setOpaque(true);
        title.setPreferredSize(new Dimension(400, 80));

        model = new DefaultTableModel();
        productTable = new JTable(model);
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(0, 60));
        importBtn = new JButton("Import Products");
        importBtn.setPreferredSize(new Dimension(180, 40));
        importBtn.setOpaque(true);
        importBtn.setBackground(c);
        importBtn.setBorderPainted(false);
        p.add(importBtn);

        model.addColumn("No.");
        model.addColumn("Product");
        model.addColumn("Size");
        model.addColumn("Import Quantity");
        scrollPane = new JScrollPane(productTable);

        initLeftGUI();
        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(leftContainer, BorderLayout.EAST);
        this.add(p, BorderLayout.SOUTH);
        handleEvents();
    }

    public void initLeftGUI() {
        leftContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
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
        refreshBtn = new JButton("Refresh");
//        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        fillColorBtn(addBtn);
        fillColorBtn(refreshBtn);
        fillColorBtn(deleteBtn);
        deleteBtn.setBackground(Color.decode("#F44336"));

        JPanel panel2 = new JPanel(new GridLayout(1, 3, 20, 0));
//        panel2.add(editBtn);
        panel2.add(deleteBtn);
        panel2.add(addBtn);
        panel2.add(refreshBtn);

        leftContainer.add(panel1);
        leftContainer.add(panel2);
    }

    private void fillColorBtn(JButton button) {
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBackground(Color.decode("#1CA7EC"));
        button.setPreferredSize(new Dimension(140, 30));
    }

    public void setDataTable(ArrayList<Object[]> objects) {
        model.setRowCount(0);
        for (Object[] o : objects) {
            model.addRow(o);
        }
    }

    public void removeAllDataTable() {
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void initComboBox() {
        String[] strings = controller.getModel().getProductNames().toArray(new String[0]);
        products = new JComboBox<>(strings);
        products.setSelectedIndex(-1);
        sizes = new JComboBox<>();
    }

    public void refreshInput() {
//        sizes.removeAllItems();
//        products.setSelectedIndex(-1);
        qtyTxf.setText("");
    }

    public void handleEvents() {

        products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizes.removeAllItems();
                String name = (String) products.getSelectedItem();
                if(name != null) {
                    String[] strings = controller.getModel().getSizesByProductName(name).toArray(new String[0]);
                    for (String string : strings) {
                        sizes.addItem(string);
                    }
                }
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] strings = controller.getModel().getProductNames().toArray(new String[0]);
                products.removeAllItems();
                for(String str : strings) {
                    products.addItem(str);
                }
                products.setSelectedIndex(-1);
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
                        controller.getModel().addPreparations(name, size, qty);
                        setDataTable(controller.getModel().getDataTable());
                        refreshInput();
                    } else {
                        throw new Exception();
                    }
                } catch (Exception exception) {
//                    System.out.println(exception);
                    JOptionPane.showMessageDialog(
                            null,
                            "Add Fail!!!",
                            "Message",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productTable.getSelectedRow() >= 0) {
                    Import selected = controller.getModel().getImportAt(productTable.getSelectedRow());
                    controller.getModel().removePreparation(selected);
                    setDataTable(controller.getModel().getDataTable());
                    refreshInput();
                    JOptionPane.showMessageDialog(
                            null,
                            "Delete Successfully!!!",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Delete Fail!!!",
                            "Message",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getModel().updateStorage();
                setDataTable(controller.getModel().getDataTable());
                refreshInput();
                JOptionPane.showMessageDialog(
                        null,
                        "Import Successfully!!!",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }

}
