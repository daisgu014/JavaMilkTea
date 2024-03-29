package Entity;

public class RevenueMonthYear {
    private int month;
    private int year;
    private int totalRevenue;
    private int totalOrders;

    public RevenueMonthYear(int month, int year, int totalRevenue, int totalOrders) {
        this.month = month;
        this.year = year;
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
    }

    public RevenueMonthYear(int month, int year, int totalRevenue) {
        this.month = month;
        this.year = year;
        this.totalRevenue = totalRevenue;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
}
