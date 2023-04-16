package App.View;

import Util.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Properties;

public class StatisticView extends JPanel {
    private JTable productTable, categoryTable, ordersTable;
    private DefaultTableModel productStatisticModel, cateStatisticModel, ordersStatisticModel;
    private JDatePickerImpl fromDatePicker, toDatePicker;
    private JButton okBtn;
    private JLabel totalSaleLbl, totalQtyLbl, title;
    private JPanel mainContainer, boardContainer, tableContainer, datePickerContainer;

    private final JLabel
            saleLbl = new JLabel("Total Sales"),
            qtyLbl = new JLabel("Quantity");

    public StatisticView() {
        init();
    }

    private void init() {
        initDateGUI();
        initBoardGUI();
        initTableGUI();

        this.setLayout(new BorderLayout());
        title = new JLabel("Statistic");
        this.add(title, BorderLayout.NORTH);

        mainContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainContainer.add(datePickerContainer);
        mainContainer.add(boardContainer);
        mainContainer.add(tableContainer);

        this.add(mainContainer, BorderLayout.CENTER);
    }



    public void initDateGUI() {
        datePickerContainer = new JPanel();

//        Set up date picker for starting date
        SqlDateModel formDateModel = new SqlDateModel();
        formDateModel.setSelected(true);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(formDateModel, p);
        // Don't know about the formatter, but there it is...
        fromDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

//        Set up date picker for finishing date
        SqlDateModel toDateModel = new SqlDateModel();
        toDateModel.setSelected(true);
        JDatePanelImpl toDatePanel = new JDatePanelImpl(toDateModel, new Properties());
        toDatePicker = new JDatePickerImpl(toDatePanel, new DateLabelFormatter());

        okBtn = new JButton("Apply");

        datePickerContainer.add(fromDatePicker);
        datePickerContainer.add(toDatePicker);
        datePickerContainer.add(okBtn);
    }

    public void initBoardGUI() {
        boardContainer = new JPanel();
//        boardContainer.setLayout(new GridLayout(2, 2, 1000, 0));

        JPanel salePanel = new JPanel();
        salePanel.setLayout(new GridLayout(2,1,0, 0));
        salePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel qtyPanel = new JPanel();
        qtyPanel.setLayout(new GridLayout(2,1,0, 0));
        qtyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        totalSaleLbl = new JLabel("100000000000000");
        totalQtyLbl = new JLabel("100000000");

        salePanel.add(saleLbl);
        salePanel.add(totalSaleLbl);

        qtyPanel.add(qtyLbl);
        qtyPanel.add(totalQtyLbl);

        boardContainer.add(salePanel);
        boardContainer.add(qtyPanel);

    }

    private void initTableGUI() {
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
        ordersStatisticModel.addColumn("Total");

        JScrollPane scrollPane = new JScrollPane(productTable);
        JScrollPane scrollPane1 = new JScrollPane(categoryTable);
        JScrollPane scrollPane2 = new JScrollPane(ordersTable);

        tableContainer = new JPanel();
        tableContainer.add(scrollPane);
        tableContainer.add(scrollPane1);
        tableContainer.add(scrollPane2);
    }



}
