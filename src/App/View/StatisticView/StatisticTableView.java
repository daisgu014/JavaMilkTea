package App.View.StatisticView;

import App.Controller.StatisticController;
import Entity.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StatisticTableView {
    private JLabel productLbl, cateLbl, orderLbl;
    private JTable productTable, categoryTable, ordersTable;
    private JScrollPane scrollPane1, scrollPane2, scrollPane3;
    private DefaultTableModel productStatisticModel, cateStatisticModel, ordersStatisticModel;
    private JPanel productContainer, cateContainer, ordersContainer;
    private StatisticController controller;

    public StatisticTableView(StatisticController controller) {
        this.controller = controller;
        initTableGUI();
    }

    public void initTableGUI() {
        Font font = new Font("", Font.BOLD, 20);
        productLbl = new JLabel("Product Revenue Table");
        cateLbl = new JLabel("Category Revenue Table");
        orderLbl = new JLabel("Orders List Table");
        productLbl.setFont(font);
        cateLbl.setFont(font);
        orderLbl.setFont(font);

        productContainer = new JPanel();
        cateContainer = new JPanel();
        ordersContainer = new JPanel();

        productStatisticModel = new DefaultTableModel();
        cateStatisticModel = new DefaultTableModel();
        ordersStatisticModel = new DefaultTableModel();

        productTable = new JTable();
        categoryTable = new JTable();
        ordersTable = new JTable();

        productTable.setModel(productStatisticModel);
        categoryTable.setModel(cateStatisticModel);
        ordersTable.setModel(ordersStatisticModel);

        productStatisticModel.addColumn("No.");
        productStatisticModel.addColumn("Product");
        productStatisticModel.addColumn("Size");
        productStatisticModel.addColumn("Total Quantity");
        productStatisticModel.addColumn("Total Sales");

        cateStatisticModel.addColumn("No.");
        cateStatisticModel.addColumn("Category");
        cateStatisticModel.addColumn("Total Sales");
        cateStatisticModel.addColumn("Quantity");

        ordersStatisticModel.addColumn("No.");
        ordersStatisticModel.addColumn("Order");
        ordersStatisticModel.addColumn("Date");
        ordersStatisticModel.addColumn("Total");

        scrollPane1 = new JScrollPane(productTable);
        scrollPane2 = new JScrollPane(categoryTable);
        scrollPane3 = new JScrollPane(ordersTable);

        scrollPane1.setPreferredSize(new Dimension(1000, 500));
        scrollPane2.setPreferredSize(new Dimension(1000, 500));
        scrollPane3.setPreferredSize(new Dimension(1000, 500));

        productContainer.setPreferredSize(new Dimension(1000, 500));
        productContainer.add(productLbl);
        productContainer.add(scrollPane1);

        cateContainer.setPreferredSize(new Dimension(1000, 500));
        cateContainer.add(cateLbl);
        cateContainer.add(scrollPane2);

        ordersContainer.setPreferredSize(new Dimension(1000, 500));
        ordersContainer.add(orderLbl);
        ordersContainer.add(scrollPane3);
        handleEvents();
    }

    public JPanel getProductContainer() {
        return productContainer;
    }

    public JPanel getCateContainer() {
        return cateContainer;
    }

    public JPanel getOrdersContainer() {
        return ordersContainer;
    }

    public DefaultTableModel getProductStatisticModel() {
        return productStatisticModel;
    }

    public DefaultTableModel getCateStatisticModel() {
        return cateStatisticModel;
    }

    public DefaultTableModel getOrdersStatisticModel() {
        return ordersStatisticModel;
    }

    public void displayData(DefaultTableModel model, ArrayList<Object[]> objects) {
        model.setRowCount(0);
        for (Object[] o: objects) {
            model.addRow(o);
        }
    }

    public void handleEvents() {
        ordersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ordersTableEvent(e);
            }
        });
    }

    private void ordersTableEvent(MouseEvent e) {
        int i = ordersTable.getSelectedRow();
        Order o = controller.getModel().getLogic().getOrders().get(
                ordersTable.getSelectionModel().getSelectedItemsCount());
        if(i > 0) {
            JOptionPane.showMessageDialog(null, "output", "hello", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
