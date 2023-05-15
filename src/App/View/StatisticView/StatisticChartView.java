package App.View.StatisticView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
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
    public static final Font font = new Font("", Font.BOLD, 20);


    public StatisticChartView() {
    }

    public StatisticChartView(DefaultCategoryDataset datasetBarChart,
                              DefaultPieDataset<String> datasetPieChart,
                              CategoryDataset datasetAreaChart) {
        initUI(datasetBarChart, datasetPieChart, datasetAreaChart);
    }

    public void initUI(DefaultCategoryDataset datasetBarChart,
                        DefaultPieDataset<String> datasetPieChart,
                       CategoryDataset datasetAreaChart) {
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

    private ChartPanel initBarChartUI(DefaultCategoryDataset dataset) {
        createBarChart(dataset);
        ChartPanel chartPanel = new ChartPanel(barChart);
        CategoryPlot categoryPlot = barChart.getCategoryPlot();

//        categoryPlot.setBackgroundPaint(SystemColor.inactiveCaption);
//        ((BarRenderer) categoryPlot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer r = (BarRenderer) categoryPlot.getRenderer();
        Color color = new Color(79, 129, 189);
        r.setSeriesPaint(0, color);
        handleNoDataset(barChart.getPlot());

        CategoryAxis domainAxis = categoryPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 30, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(1200, 500));

        return chartPanel;
    }

    private void createBarChart(CategoryDataset dataset) {
            barChart = ChartFactory.createBarChart(
                "Product Revenue",
                "",
                "VND",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
            barChart.getTitle().setFont(font);

    }

    private ChartPanel initPieChartUI(DefaultPieDataset<String> dataset) {
        createPieChart(dataset);
        ChartPanel chartPanel = new ChartPanel(pieChart);
        pieChart.getPlot().setOutlinePaint(null);
        pieChart.getPlot().setBackgroundPaint(Color.white);
        handleNoDataset(pieChart.getPlot());

        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(1000, 500));
        return chartPanel;
    }

    private void createPieChart(DefaultPieDataset<String> dataset) {
        pieChart = ChartFactory.createPieChart(
                "Category Revenue",
                dataset,
                true, true, false);
        pieChart.getTitle().setFont(font);
    }

    private JPanel initAreaChartUI(CategoryDataset dataset) {
        createAreaChartChart(dataset);
        ChartPanel chartPanel = new ChartPanel(areaChart);
        handleNoDataset(areaChart.getPlot());

        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize(new Dimension(1200, 400));
//        areaChart.getPlot().setBackgroundPaint(Color.white);
//        areaChart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.black);
        return chartPanel;
    }

    private void createAreaChartChart(CategoryDataset dataset) {

        areaChart = ChartFactory.createAreaChart(
                "Total Revenue",
                "Time",
                "VND",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );

        CategoryPlot plot = (CategoryPlot) areaChart.getPlot();
        plot.setForegroundAlpha(0.6f);

        AreaRenderer renderer = (AreaRenderer) plot.getRenderer();
        renderer.setEndType(AreaRendererEndType.LEVEL);

        areaChart.getTitle().setFont(font);
    }

    public void setDataset(DefaultCategoryDataset datasetBarChart,
                           DefaultPieDataset<String> datasetPieChart,
                           CategoryDataset datasetAreaChart) {
        barChart.getCategoryPlot().setDataset(datasetBarChart);
        ((PiePlot) pieChart.getPlot()).setDataset(datasetPieChart);
        areaChart.getCategoryPlot().setDataset(datasetAreaChart);
    }

    private void handleNoDataset(Plot plot) {
        plot.setNoDataMessage("No data available");
        plot.setNoDataMessageFont(new Font("SansSerif", Font.BOLD, 14));
        plot.setNoDataMessagePaint(Color.BLACK);
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
