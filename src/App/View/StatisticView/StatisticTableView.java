package App.View.StatisticView;

import App.Controller.StatisticController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static App.View.LoginView.primary;
import static App.View.LoginView.yellow;

public class StatisticTableView {
    private JLabel productLbl, cateLbl, orderLbl;
    private JTable productTable, categoryTable, revenueTable;
    private JScrollPane scrollPane1, scrollPane2, scrollPane3;
    private DefaultTableModel productStatisticModel, cateStatisticModel, revenueModel;
    private JPanel productContainer, cateContainer, revenueContainer;
    private JButton exportBtn;
    private StatisticController controller;

    public StatisticTableView(StatisticController controller) {
        this.controller = controller;
        initTableGUI();
        initBottomGUI();
        handleEvents();
    }

    public void initTableGUI() {
        Font font = new Font("", Font.BOLD, 20);
        productLbl = new JLabel("Product Revenue Table");
        cateLbl = new JLabel("Category Revenue Table");
        orderLbl = new JLabel("Revenue Monthly Table");
        productLbl.setFont(font);
        cateLbl.setFont(font);
        orderLbl.setFont(font);

        productContainer = new JPanel();
        cateContainer = new JPanel();
        revenueContainer = new JPanel();

        productStatisticModel = new DefaultTableModel();
        cateStatisticModel = new DefaultTableModel();
        revenueModel = new DefaultTableModel();

        productTable = new JTable();
        fillColorTable(productTable);
        categoryTable = new JTable();
        fillColorTable(categoryTable);
        revenueTable = new JTable();
        fillColorTable(revenueTable);

        productTable.setModel(productStatisticModel);
        categoryTable.setModel(cateStatisticModel);
        revenueTable.setModel(revenueModel);

        productStatisticModel.addColumn("No.");
        productStatisticModel.addColumn("Product");
        productStatisticModel.addColumn("Size");
        productStatisticModel.addColumn("Total Quantity");
        productStatisticModel.addColumn("Total Sales (Not Include Discount)");

        cateStatisticModel.addColumn("No.");
        cateStatisticModel.addColumn("Category");
        cateStatisticModel.addColumn("Total Sales (Not Include Discount)");
        cateStatisticModel.addColumn("Quantity");

        revenueModel.addColumn("No.");
        revenueModel.addColumn("Date");
        revenueModel.addColumn("Total Revenue (Include Discount)");
        revenueModel.addColumn("Total Quantity Orders");

        scrollPane1 = new JScrollPane(productTable);
        scrollPane2 = new JScrollPane(categoryTable);
        scrollPane3 = new JScrollPane(revenueTable);

        scrollPane1.setPreferredSize(new Dimension(1100, 500));
        scrollPane2.setPreferredSize(new Dimension(1100, 500));
        scrollPane3.setPreferredSize(new Dimension(1100, 500));

        productContainer.setPreferredSize(new Dimension(1200, 550));
        productContainer.setBackground(yellow);
        productContainer.add(productLbl);
        productContainer.add(scrollPane1);

        cateContainer.setPreferredSize(new Dimension(1200, 550));
        cateContainer.setBackground(yellow);
        cateContainer.add(cateLbl);
        cateContainer.add(scrollPane2);

        revenueContainer.setPreferredSize(new Dimension(1200, 550));
        revenueContainer.setBackground(yellow);
        revenueContainer.add(orderLbl);
        revenueContainer.add(scrollPane3);
    }

    public void initBottomGUI() {
        exportBtn = new JButton("Export Excel");
        exportBtn.setPreferredSize(new Dimension(180, 40));
        exportBtn.setBackground(primary);
        exportBtn.setOpaque(true);
        exportBtn.setBorderPainted(false);
    }

    private void fillColorTable(JTable table) {
        table.getTableHeader().setBackground(primary);
        table.getTableHeader().setFont(new Font("", Font.BOLD, 13));
    }

    public JPanel getProductContainer() {
        return productContainer;
    }

    public JPanel getCateContainer() {
        return cateContainer;
    }

    public JPanel getRevenueContainer() {
        return revenueContainer;
    }

    public JButton getExportBtn() {
        return exportBtn;
    }

    public DefaultTableModel getProductStatisticModel() {
        return productStatisticModel;
    }

    public DefaultTableModel getCateStatisticModel() {
        return cateStatisticModel;
    }

    public DefaultTableModel getRevenueModel() {
        return revenueModel;
    }

    public void displayData(DefaultTableModel model, ArrayList<Object[]> objects) {
        model.setRowCount(0);
        for (Object[] o: objects) {
            model.addRow(o);
        }
    }

    public void handleEvents() {
        exportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<JTable> tables = new ArrayList<>();
                tables.add(revenueTable);
                tables.add(productTable);
                tables.add(categoryTable);
                ArrayList<String> titles = new ArrayList<>();
                titles.add("Revenue Table");
                titles.add("Product Revenue Table");
                titles.add("Category Revenue Table");
                controller.getModel().exportExcel(tables, titles);
                JOptionPane.showMessageDialog(
                        null,
                        "Export Successfully",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}
