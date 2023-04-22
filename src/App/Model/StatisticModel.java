package App.Model;

import App.View.Shop.loadData;
import Entity.Order;
import Entity.StatisticCategory;
import Entity.StatisticProduct;
import Logic.StatisticManagement;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

public class StatisticModel {
    private StatisticManagement logic;

    public StatisticModel() {
        logic = loadData.management.getStatisticManagement();
    }

    public void getData(Date from, Date to) {
        logic.getDataStatistic(from, to);
    }

    public DefaultPieDataset<String> getDataPieChart() {
        return logic.getDataPieChart();
    }

    public DefaultCategoryDataset getDataBarChart() {
        return logic.getDataBarChart();
    }
    public CategoryDataset getDataRevenueChart() {
        return logic.getDataRevenueChart();
    }

    public ArrayList<Object[]> getDataProductTable() {
        ArrayList<Object[]> strings = new ArrayList<>();
        for(StatisticProduct sp : logic.getProductStatistic()) {
            String s = "";
            for(Map.Entry<String, Integer> item : sp.getProductCountBySize().entrySet()) {
                s += String.format("Size %s: %d\n", item.getKey(), item.getValue());
            }
            strings.add(new String[] {
                    String.valueOf(sp.getProductId()),
                    sp.getProductName(),
                    s,
                    String.valueOf(sp.getProductCount()),
                    String.valueOf(sp.getTotalRevenue())
            });
        }
        return strings;
    }

    public ArrayList<Object[]> getDataCateTable() {
        ArrayList<Object[]> list = new ArrayList<>();
        int i = 1;
        for(StatisticCategory sc : logic.getCategoryStatistic()) {
            list.add(new String[] {
                    String.valueOf(i++),
                    sc.getName(),
                    String.valueOf(sc.getTotalRevenue()),
                    String.valueOf(sc.getProductCount())
            });
        }
        return list;
    }

    public ArrayList<Object[]> getDataOrderTable() {
        ArrayList<Object[]> list = new ArrayList<>();
        int i = 1;
        for(Order o : logic.getOrders()) {
            list.add(new String[] {
                    String.valueOf(i++),
                    String.valueOf(o.getOrderId()),
                    String.valueOf(o.getOrderDate()),
                    String.valueOf(o.getTotalPrice())
            });
        }
        return list;
    }

    public StatisticManagement getLogic() {
        return logic;
    }
}
