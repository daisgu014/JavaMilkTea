package App.Model;

import App.View.Shop.loadData;
import Entity.Order;
import Entity.RevenueMonthYear;
import Entity.StatisticCategory;
import Entity.StatisticProduct;
import Logic.StatisticManagement;
import Util.ExcelTool;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

public class StatisticModel {
    private StatisticManagement logic;
    private static final String pathExcel = "src/Files/";

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

    public ArrayList<Object[]> getDataRevenueTable() {
        ArrayList<Object[]> list = new ArrayList<>();
        int i = 1;
        for(RevenueMonthYear r : logic.getRevenueMYList()) {
            list.add(new String[] {
                    String.valueOf(i++),
                    "M-Y: "+r.getMonth()+"-"+r.getYear(),
                    String.valueOf(r.getTotalRevenue()),
                    String.valueOf(r.getTotalOrders())
            });
        }
        return list;
    }

    public ArrayList<Integer> getTotalSQ() {
        return logic.getTotalSQ();
    }

    public StatisticManagement getLogic() {
        return logic;
    }

    public void exportExcel(ArrayList<JTable> tables, ArrayList<String> titles) {
        ExcelTool excelTool = new ExcelTool();
        String excelFilePath = pathExcel+ "revenue" + new java.util.Date().toString().trim()+".xlsx";
        excelTool.writeExcel(tables, titles, excelFilePath);
//        System.setProperty("log4j.configurationFile","./path_to_the_log4j2_config_file/log4j2.xml");
//        Logger log = LogManager.getLogger(LogExample.class.getName());
    }
}
