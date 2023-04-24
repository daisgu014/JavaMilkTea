package Logic;

import App.View.Shop.loadData;
import Entity.*;
import DAL.StatisticDAO;
import Main.Main;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Date;
import java.util.ArrayList;

public class StatisticManagement {
    StatisticDAO statisticAccess;
    ArrayList<StatisticProduct> productStatistic;
    ArrayList<StatisticCategory> categoryStatistic;
    ArrayList<RevenueMonthYear> revenueMYList;
    ArrayList<Integer> totalSQ;

    public StatisticManagement() {
        statisticAccess = new StatisticDAO();
    }

    public void getDataStatistic(Date from, Date to) {
        productStatistic = statisticAccess.getProductStatistic(from, to);
        categoryStatistic = statisticAccess.getCategoryStatistic(from, to);
        revenueMYList = statisticAccess.getRevenueMonthYear(from, to);
        totalSQ = statisticAccess.getTotal(from, to);
    }

    public DefaultPieDataset<String> getDataPieChart() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (StatisticCategory sc : categoryStatistic) {
            dataset.setValue(sc.getName(), sc.getTotalRevenue());
        }
        return dataset;
    }

    public DefaultCategoryDataset getDataBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        boolean flag;
        for (Product p : loadData.management.getProductManagement().getProducts()) {
            flag = true;
            for(StatisticProduct sp : productStatistic) {
                if(p.getProductId() == sp.getProductId()) {
                    dataset.setValue(sp.getTotalRevenue(), "Total Revenue", sp.getProductName());
                    flag = false;
                    break;
                }
            }
            if (flag) {
                dataset.setValue(null, "Total Revenue", p.getProductName());
            }
        }
        return dataset;
    }

    public CategoryDataset getDataRevenueChart() {
        int l = revenueMYList.size();
        double[][] data = new double[1][l];
        String[] rowKeys = new String[1];
        rowKeys[0] = "Revenue Monthly";
        String[] columKeys = new String[l];
        int i = 0;
        for(RevenueMonthYear r : revenueMYList) {
            columKeys[i] = "T" + r.getMonth() + "/" + r.getYear();
            data[0][i] = r.getTotalRevenue();
            i++;
        }

        CategoryDataset dataset = DatasetUtils.createCategoryDataset(
                rowKeys,
                columKeys,
                data
        );
        return dataset;
    }

    public ArrayList<StatisticProduct> getProductStatistic(Date from, Date to) {
        return statisticAccess.getProductStatistic(from, to);
    }

    public ArrayList<StatisticCategory> getCategoryStatistic(Date from, Date to) {
        return statisticAccess.getCategoryStatistic(from, to);
    }

    public ArrayList<StatisticProduct> getProductStatistic() {
        return productStatistic;
    }

    public ArrayList<StatisticCategory> getCategoryStatistic() {
        return categoryStatistic;
    }

    public ArrayList<RevenueMonthYear> getRevenueMYList() {
        return revenueMYList;
    }

    public ArrayList<Integer> getTotalSQ() {
        return totalSQ;
    }
}
