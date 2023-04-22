package Entity;

import com.mysql.cj.conf.ConnectionUrlParser;
import java.util.ArrayList;
import java.util.HashMap;

public class StatisticProduct {
    private int productId;
    private String productName;
    private int productCount;
    private HashMap<String, Integer> productCountBySize;
    private int totalRevenue;

    public StatisticProduct(int productId, String productName, int productCount, HashMap<String, Integer> productCountBySize, int totalRevenue) {
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
        this.setProductCountBySize(new HashMap<>());
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

    public HashMap<String, Integer> getProductCountBySize() {
        return productCountBySize;
    }

    public void setProductCountBySize(HashMap<String, Integer> productCountBySize) {
        this.productCountBySize = productCountBySize;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void addProductCountBySize(String size, Integer quantity) {
        productCountBySize.put(size, quantity);
    }
}
