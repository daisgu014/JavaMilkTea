package App.Model;

import Logic.StatisticManagement;
import Main.Main;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Date;

public class StatisticModel {
    private StatisticManagement logic;

    public StatisticModel() {
        logic = Main.management.getStatisticManagement();
    }

    public void getData(Date from, Date to) {
        logic.getDataStatistic(from, to);
    }

    public DefaultPieDataset<String> getDataPieChart() {
        return logic.getDataPieChart();
    }

}
