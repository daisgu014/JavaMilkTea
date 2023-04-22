package Entity;

public class Customer {
    private String phone;
    private String customerName;
    private int points;

    public Customer() {
    }

    public Customer(String phone, String customerName, int points) {
        this.phone = phone;
        this.customerName = customerName;
        this.points = points;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public Object[] toObject(){
        return new String[]{getPhone(), getCustomerName(), String.valueOf(points)};
    }
}

