package Entity;

import com.mysql.cj.conf.ConnectionUrlParser;
import java.util.ArrayList;

public class StatisticProduct {
    private int productId;
    private String productName;
    private int productCount;
    private ArrayList<ConnectionUrlParser.Pair<String,Number>> productCountBySize;
    private int totalRevenue;

    public StatisticProduct(int productId, String productName, int productCount,
                            ArrayList<ConnectionUrlParser.Pair<String, Number>> productCountBySize, int totalRevenue) {
        this.productId = productId;
        this.productName = productName;
        this.productCount = productCount;
        this.productCountBySize = productCountBySize;
        this.totalRevenue = totalRevenue;
    }

    public StatisticProduct(int productId, String productName, int productCount, int totalRevenue) {
        this.productId = productId;
        this.productName = productName;
        this.productCount = productCount;
        this.totalRevenue = totalRevenue;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
