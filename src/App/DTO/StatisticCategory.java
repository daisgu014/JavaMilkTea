package App.DTO;

public class StatisticCategory {
    private int categoryId;
    private String name;
    private Number productCount;
    private Number totalRevenue;

    public StatisticCategory(int categoryId, String name, Number productCount, Number totalRevenue) {
        this.categoryId = categoryId;
        this.name = name;
        this.productCount = productCount;
        this.totalRevenue = totalRevenue;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
