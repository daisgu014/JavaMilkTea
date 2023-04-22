package App.View.StatisticView;

import App.Controller.StatisticController;
import Util.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Properties;

public class StatisticView extends JPanel {
    private JDatePickerImpl fromDatePicker, toDatePicker;
    private JButton applyBtn;
    private JLabel totalSaleLbl, totalQtyLbl;
    private JPanel boardContainer, datePickerContainer, chartContainer, tableContainer;
    private JScrollPane scrollPaneChart, scrollPaneTable;
    private JTabbedPane tabbedPane;
    private StatisticChartView chartView;
    private StatisticTableView tableView;
    private StatisticController controller;

    private final JLabel
            saleLbl = new JLabel("Total Sales"),
            qtyLbl = new JLabel("Quantity");

    public StatisticView() {
        init();
    }

    private void init() {
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout());

        controller = new StatisticController();
        chartView = new StatisticChartView();
        tableView = new StatisticTableView(controller);
        initDateGUI();
        initBoardGUI();
        initDataView();
        this.add(datePickerContainer);
        this.add(boardContainer);
        this.add(tabbedPane);

        handleEvents();
    }

    public void initDataView() {
        getData();
        renderChartView();
        renderTableView();
        display();
    }

    private void display() {
        FlowLayout fl = new FlowLayout();
        fl.setVgap(100);
        chartContainer = new JPanel(fl);
        chartContainer.setPreferredSize(new Dimension(1000, 2000));
        tableContainer = new JPanel(fl);
        tableContainer.setPreferredSize(new Dimension(1000, 2000));


        chartContainer.add(chartView.getAreaChartPanel());
        chartContainer.add(chartView.getPieChartPanel());
        chartContainer.add(chartView.getBarChartPanel());
        tableContainer.add(tableView.getProductContainer());
        tableContainer.add(tableView.getCateContainer());
        tableContainer.add(tableView.getOrdersContainer());

        scrollPaneChart = new JScrollPane(chartContainer);
        scrollPaneChart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneChart.setPreferredSize(new Dimension(1200, 700));
        scrollPaneTable = new JScrollPane(tableContainer);
        scrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneTable.setPreferredSize(new Dimension(1200, 700));

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Chart View", scrollPaneChart);
        tabbedPane.add("Table View", scrollPaneTable);
        tabbedPane.setPreferredSize(new Dimension(1200, 700));
    }

    public void getData() {
        try {
            controller.getDataFromTime(
                    (Date) fromDatePicker.getModel().getValue(),
                    (Date) toDatePicker.getModel().getValue()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void renderChartView() {
        chartView.initUI(
                controller.getModel().getDataBarChart(),
                controller.getModel().getDataPieChart(),
                controller.getModel().getDataRevenueChart()
        );
    }

    private void renderTableView() {
        tableView.displayData(tableView.getProductStatisticModel(), controller.getModel().getDataProductTable());
        tableView.displayData(tableView.getCateStatisticModel(), controller.getModel().getDataCateTable());
        tableView.displayData(tableView.getOrdersStatisticModel(), controller.getModel().getDataOrderTable());
    }

    public void initDateGUI() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
//        mouth + 1 bacause month [0, 11], when month is set in SqlDateModel, do not need +1
        YearMonth yearMonthObject = YearMonth.of(year, (month + 1));
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
        salePanel.setLayout(new GridLayout(2, 1, 0, 0));
        salePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel qtyPanel = new JPanel();
        qtyPanel.setLayout(new GridLayout(2, 1, 0, 0));
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

    public void handleEvents() {
        this.applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getData();
                chartView.setDataset(
                        controller.getModel().getDataBarChart(),
                        controller.getModel().getDataPieChart(),
                        controller.getModel().getDataRevenueChart()
                );
                renderTableView();
            }
        });
    }

    public StatisticController getController() {
        return controller;
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
