package App.View.StatisticView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class StatisticChartView {
    private JFreeChart barChart;
    private JFreeChart pieChart;
    private JFreeChart areaChart;
    private JPanel barChartPanel, pieChartPanel, areaChartPanel;


    public StatisticChartView() {
    }

    public StatisticChartView(DefaultCategoryDataset datasetBarChart,
                              DefaultPieDataset<String> datasetPieChart,
                              DefaultCategoryDataset datasetAreaChart) {
        initUI(datasetBarChart, datasetPieChart, datasetAreaChart);
    }

    public void initUI(DefaultCategoryDataset datasetBarChart,
                        DefaultPieDataset<String> datasetPieChart,
                        DefaultCategoryDataset datasetAreaChart) {
        barChartPanel = initBarChartUI(datasetBarChart);
        pieChartPanel = initPieChartUI(datasetPieChart);
        areaChartPanel = initAreaChartUI(datasetAreaChart);
    }

    public JPanel getBarChartPanel() {
        return barChartPanel;
    }

    public JPanel getPieChartPanel() {
        return pieChartPanel;
    }

    public JPanel getAreaChartPanel() {
        return areaChartPanel;
    }

    public ChartPanel initBarChartUI(DefaultCategoryDataset dataset) {

        dataset.setValue(46, "Gold medals", "USA");
        dataset.setValue(38, "Gold medals", "China");
        dataset.setValue(29, "Gold medals", "UK");
        dataset.setValue(22, "Gold medals", "Russia");
        dataset.setValue(13, "Gold medals", "South Korea");
        dataset.setValue(11, "Gold medals", "Germany");

        createBarChart(dataset);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(1200, 300));

        return chartPanel;
    }

    private void createBarChart(CategoryDataset dataset) {
            barChart = ChartFactory.createBarChart(
                "Product Revenue",
                "",
                "VND",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

    }

    public ChartPanel initPieChartUI(DefaultPieDataset<String> dataset) {

//        dataset.setValue("Apache", 52);
//        dataset.setValue("Nginx", 31);
//        dataset.setValue("IIS", 12);
//        dataset.setValue("LiteSpeed", 2);
//        dataset.setValue("Google server", 1);
//        dataset.setValue("Others", 2);

        createPieChart(dataset);
        ChartPanel chartPanel = new ChartPanel(pieChart);
        pieChart.getPlot().setOutlinePaint(null);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(1200, 500));
        return chartPanel;
    }

    private void createPieChart(DefaultPieDataset<String> dataset) {
         pieChart = ChartFactory.createPieChart(
                "Category Revenue",
                dataset,
                false, true, false);

    }

    private JPanel initAreaChartUI(CategoryDataset dataset) {
        double[][] data = new double[][]{
                {82502, 84026, 85007, 86216, 85559, 84491, 87672,
                        88575, 89837, 90701}
        };

        dataset = DatasetUtils.createCategoryDataset(
                new String[]{"Oil"}, new String[]{"2004", "2005", "2006",
                        "2007", "2008", "2009", "2010", "2011", "2012", "2013"},
                data
        );
        createAreaChartChart(dataset);
        ChartPanel chartPanel = new ChartPanel(areaChart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(1200, 400));
        return chartPanel;
    }

    private void createAreaChartChart(CategoryDataset dataset) {

        areaChart = ChartFactory.createAreaChart(
                "",
                "Time",
                "Thousands bbl/day",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                true
        );

        CategoryPlot plot = (CategoryPlot) areaChart.getPlot();
        plot.setForegroundAlpha(0.6f);

        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);

        areaChart.setTitle(new TextTitle("Total Revenue",
                new Font("Serif", java.awt.Font.BOLD, 18))
        );

    }

    //    private CategoryDataset createDataset() {
//
//        double[][] data = new double[][]{
//                {82502, 84026, 85007, 86216, 85559, 84491, 87672,
//                        88575, 89837, 90701}
//        };
//
//        CategoryDataset dataset = DatasetUtils.createCategoryDataset(
//                new String[]{"Oil"}, new String[]{"2004", "2005", "2006",
//                        "2007", "2008", "2009", "2010", "2011", "2012", "2013"},
//                data
//        );
//
//        return dataset;
//    }

    //    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//            var ex = new StatisticChartView();
//            ex.setVisible(true);
//        });
//    }

    //    private DefaultPieDataset createDataset() {
//
//        var dataset = new DefaultPieDataset();
//        dataset.setValue("Apache", 52);
//        dataset.setValue("Nginx", 31);
//        dataset.setValue("IIS", 12);
//        dataset.setValue("LiteSpeed", 2);
//        dataset.setValue("Google server", 1);
//        dataset.setValue("Others", 2);
//
//        return dataset;
//    }

//    private CategoryDataset createDataset() {
//
//        var dataset = new DefaultCategoryDataset();
//        dataset.setValue(46, "Gold medals", "USA");
//        dataset.setValue(38, "Gold medals", "China");
//        dataset.setValue(29, "Gold medals", "UK");
//        dataset.setValue(22, "Gold medals", "Russia");
//        dataset.setValue(13, "Gold medals", "South Korea");
//        dataset.setValue(11, "Gold medals", "Germany");
//
//        return dataset;
//    }
}
