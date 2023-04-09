package App.Model;

public class StatisticCategoryModel {
    private String name;
    private Number productCount;
    private Number totalRevenue;

    public StatisticCategoryModel(String name, Number productCount, Number totalRevenue) {
        this.name = name;
        this.productCount = productCount;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getProductCount() {
        return productCount;
    }

    public void setProductCount(Number productCount) {
        this.productCount = productCount;
    }

    public Number getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Number totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
