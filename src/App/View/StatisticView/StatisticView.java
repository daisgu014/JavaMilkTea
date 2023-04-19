package App.View.StatisticView;

import App.Controller.StatisticController;
import Util.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Properties;

public class StatisticView extends JPanel {
    private JTable productTable, categoryTable, ordersTable;
    private JScrollPane scrollPane1, scrollPane2, scrollPane3;
    private DefaultTableModel productStatisticModel, cateStatisticModel, ordersStatisticModel;
    private JDatePickerImpl fromDatePicker, toDatePicker;
    private JButton applyBtn;
    private JLabel totalSaleLbl, totalQtyLbl;
    private JPanel boardContainer, datePickerContainer;
    private StatisticChartView chartView;
    private StatisticController controller;

    private final JLabel
            saleLbl = new JLabel("Total Sales"),
            qtyLbl = new JLabel("Quantity");

    public StatisticView() {
        init();
    }

    private void init() {
        this.setBackground(Color.white);
        FlowLayout flowLayout = new FlowLayout();
//        flowLayout.setHgap(300);
        this.setLayout(flowLayout);
        this.setPreferredSize(new Dimension(1000, 5000));

        controller = new StatisticController();

        initDateGUI();
        initBoardGUI();
        initTableGUI();
        initChartView();

        this.add(datePickerContainer);
        this.add(boardContainer);
        this.add(chartView.getPieChartPanel());
        this.add(chartView.getBarChartPanel());
        this.add(chartView.getAreaChartPanel());
        this.add(scrollPane1);
        this.add(scrollPane2);
        this.add(scrollPane3);

        handleEvents();
    }

    public void initChartView() {
        chartView = new StatisticChartView();
        try {
            controller.getDataFromTime(
                    (Date) fromDatePicker.getModel().getValue(),
                    (Date) toDatePicker.getModel().getValue()
            );
            chartView.initUI(
                    controller.getModel().getDataBarChart(),
                    controller.getModel().getDataPieChart(),
                    controller.getModel().getDataRevenueChart()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initDateGUI() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
//        mouth + 1 bacause month [0, 11], when month is set in SqlDateModel, do not need +1
        YearMonth yearMonthObject = YearMonth.of(year, (month+1));
        int daysInMonth = yearMonthObject.lengthOfMonth();

        datePickerContainer = new JPanel();
//        Set up date picker for starting date
        SqlDateModel formDateModel = new SqlDateModel();
        formDateModel.setDate(year, month, 1);
        formDateModel.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(formDateModel, new Properties());
//         Need this...
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        JDatePanelImpl datePanel = new JDatePanelImpl(formDateModel, p);
//         Don't know about the formatter, but there it is...
        fromDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

//        Set up date picker for finishing date
        SqlDateModel toDateModel = new SqlDateModel();
        toDateModel.setDate(year, month, daysInMonth);
        toDateModel.setSelected(true);
        JDatePanelImpl toDatePanel = new JDatePanelImpl(toDateModel, new Properties());
        toDatePicker = new JDatePickerImpl(toDatePanel, new DateLabelFormatter());

        applyBtn = new JButton("Apply");

        datePickerContainer.add(fromDatePicker);
        datePickerContainer.add(toDatePicker);
        datePickerContainer.add(applyBtn);
    }

    public void initBoardGUI() {
        boardContainer = new JPanel();

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

        scrollPane1 = new JScrollPane(productTable);
        scrollPane2 = new JScrollPane(categoryTable);
        scrollPane3 = new JScrollPane(ordersTable);

        scrollPane1.setPreferredSize(new Dimension(800, 500));
        scrollPane2.setPreferredSize(new Dimension(800, 500));
        scrollPane3.setPreferredSize(new Dimension(800, 500));
    }

    public StatisticController getController() {
        return controller;
    }

    public void handleEvents() {
        this.applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("hello");
                    controller.getDataFromTime(
                            (Date) fromDatePicker.getModel().getValue(),
                            (Date) toDatePicker.getModel().getValue()
                    );
                    chartView.initUI(
                            controller.getModel().getDataBarChart(),
                            controller.getModel().getDataPieChart(),
                            controller.getModel().getDataRevenueChart()
                    );
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

    }

    //    public static void reSize(Component parent, Container children) {
//        double sumW = 0;
//        double maxH = 0;
//        for(Component c : children.getComponents()) {
//            sumW += c.getWidth();
//            maxH = Math.max(maxH, c.getHeight());
//        }
//        double rowCount = Math.ceil(sumW/parent.getWidth());
//        children.setPreferredSize(new Dimension(parent.getWidth(), (int)(rowCount * maxH)));
//        System.out.println(parent.getWidth());
//        System.out.println((int)(rowCount * maxH));
//        System.out.println(sumW/parent.getWidth());
//    }

}
