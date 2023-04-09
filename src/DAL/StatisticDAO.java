package DAL;

import App.Model.StatisticProductModel;
import Entity.Order;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StatisticDAO{
    private Database statisticDAO = new Database();

    public ArrayList<StatisticProductModel> getProductStatistic() {
        Statement stmt = statisticDAO.getStmt();
        try {
            ResultSet rs = stmt.executeQuery("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Order> getOrderStatistic(Date start, Date end) {
        return null;
    }


}
