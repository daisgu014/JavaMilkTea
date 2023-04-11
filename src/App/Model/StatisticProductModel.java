package App.Model;

import com.mysql.cj.conf.ConnectionUrlParser;
import java.util.ArrayList;

public class StatisticProductModel {
    private String productName;
    private int productCount;
    private ArrayList<ConnectionUrlParser.Pair<String,Number>> productCountBySize;
    private int totalRevenue;

    public StatisticProductModel(String productName, int productCount,
                                 ArrayList<ConnectionUrlParser.Pair<String, Number>> productCountBySize,
                                 int totalRevenue) {
        this.productName = productName;
        this.productCount = productCount;
        this.productCountBySize = productCountBySize;
        this.totalRevenue = totalRevenue;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public ArrayList<ConnectionUrlParser.Pair<String, Number>> getProductCountBySize() {
        return productCountBySize;
    }

    public void setProductCountBySize(ArrayList<ConnectionUrlParser.Pair<String, Number>> productCountBySize) {
        this.productCountBySize = productCountBySize;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
