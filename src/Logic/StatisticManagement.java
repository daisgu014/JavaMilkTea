package Logic;

import App.DTO.StatisticCategory;
import App.DTO.StatisticProduct;
import DAL.StatisticDAO;
import Entity.Order;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Date;
import java.util.ArrayList;

public class StatisticManagement {
    StatisticDAO statisticAccess;
    ArrayList<StatisticProduct> productStatistic;
    ArrayList<StatisticCategory> categoryStatistic;
    ArrayList<Order> orders;

    public StatisticManagement() {
        statisticAccess = new StatisticDAO();
    }

    public void getDataStatistic(Date from, Date to) {
        productStatistic = statisticAccess.getProductStatistic(from, to);
        categoryStatistic = statisticAccess.getCategoryStatistic(from, to);
//        orders = statisticAccess.getOrderStatistic(from, to);
    }

    public DefaultPieDataset<String> getDataPieChart() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (StatisticCategory sc : categoryStatistic) {
            dataset.setValue(sc.getName(), sc.getTotalRevenue());
        }
        return dataset;
    }

    public ArrayList<StatisticProduct> getProductStatistic(Date from, Date to) {
        return statisticAccess.getProductStatistic(from, to);
    }

    public ArrayList<StatisticCategory> getCategoryStatistic(Date from, Date to) {
        return statisticAccess.getCategoryStatistic(from, to);
    }

    public ArrayList<Order> getOrders(Date from, Date to) {
        return statisticAccess.getOrderStatistic(from, to);
    }
}
