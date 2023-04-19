package App.Model;

import App.View.Shop.loadData;
import Logic.StatisticManagement;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Date;

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

}
